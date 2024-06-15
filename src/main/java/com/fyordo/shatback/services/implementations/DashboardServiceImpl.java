package com.fyordo.shatback.services.implementations;

import com.fyordo.shatback.dto.responses.dashboard.DashboardAllResponse;
import com.fyordo.shatback.dto.responses.project.ProjectResponse;
import com.fyordo.shatback.dto.responses.task.TaskShortResponse;
import com.fyordo.shatback.dto.task.CurrentTaskDto;
import com.fyordo.shatback.dto.task.TrackedTaskDto;
import com.fyordo.shatback.entities.Project;
import com.fyordo.shatback.entities.Task;
import com.fyordo.shatback.entities.TimeEntry;
import com.fyordo.shatback.exceptions.ModelNotFoundException;
import com.fyordo.shatback.repos.TaskRepository;
import com.fyordo.shatback.services.DashboardService;
import com.fyordo.shatback.services.ProjectService;
import com.fyordo.shatback.services.TimeEntryService;
import com.fyordo.shatback.services.auth.SecurityWorkspace;
import com.rentinhand.rihtracker.dto.TimePeriodDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
   private final TaskRepository taskRepository;


    private final SecurityWorkspace securityWorkspace;
    private final TimeEntryService timeEntryService;
    private final ProjectService projectService;

    public DashboardAllResponse getAll(Long projectId) {
        Optional<Project> projectReq = projectService.findById(projectId);
        if(projectReq.isEmpty()){
            throw new ModelNotFoundException();
        }
        Project project = projectReq.get();

        DashboardAllResponse dashboard = new DashboardAllResponse();

        dashboard.setTodayTime(this.getTodayEntries(project));
        dashboard.setCurrentTask(this.getCurrentTask());
        List<Task> tasks = taskRepository.getAllAttachedByProjectId(projectId, securityWorkspace.getAuthUserId());
        dashboard.setCountTasks(tasks.size());

        dashboard.setGraph(getMonthWorkHoursStatistic(project));

        return dashboard;
    }

    private List<TimePeriodDto> getMonthWorkHoursStatistic(Project project){
        LocalDate currentDate = LocalDate.now();

        LocalDate startOfMonth = currentDate.withDayOfMonth(1);
        LocalDate endOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());

        List<TimePeriodDto> monthStatistics = new ArrayList<>();

        LocalDate dateIterator = startOfMonth;
        while (!dateIterator.isAfter(endOfMonth)) {
            List<TimeEntry> timeEntries = timeEntryService.getEntriesByDateAndProjectId(dateIterator, project);

            Duration totalDuration = calculateTotalDuration(timeEntries);

            TimePeriodDto dayStatistic = new TimePeriodDto(totalDuration, dateIterator);
            monthStatistics.add(dayStatistic);

            dateIterator = dateIterator.plusDays(1);
        }

        return monthStatistics;
    }


    private Duration calculateTotalDuration(List<TimeEntry> timeEntries) {
        return timeEntries.stream()
                .map(entry -> Duration.between(entry.getTimeStart(), entry.getTimeEnd()))
                .reduce(Duration.ZERO, Duration::plus);
    }

    private TimePeriodDto getTodayEntries(Project project) {
        List<TimeEntry> timeEntries = timeEntryService.getEntriesByDateAndProjectId(LocalDate.now(), project);
        Duration totalDuration = timeEntries.stream()
                .map(entry -> Duration.between(entry.getTimeStart(), entry.getTimeEnd()))
                .reduce(Duration.ZERO, Duration::plus);
        return new TimePeriodDto(totalDuration);
    }

    private CurrentTaskDto getCurrentTask() {
        Optional<TimeEntry> currentTimeEntry = timeEntryService.getCurrentTimeEntry();
        CurrentTaskDto currentTaskDto = new CurrentTaskDto();
        if (currentTimeEntry.isPresent()) {
            TimeEntry timeEntry = currentTimeEntry.get();
            Task currentTask = timeEntry.getTask();
            currentTaskDto.setProject(new ProjectResponse(currentTask.getScrumColumn().getProject()));
            currentTaskDto.setTrackedTask(new TrackedTaskDto(timeEntry.getTimeStart(), new TaskShortResponse(currentTask)));
        }
        return currentTaskDto;
    }

}
