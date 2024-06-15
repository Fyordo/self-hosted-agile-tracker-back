package com.rentinhand.rihtracker.services.implementations;

import com.rentinhand.rihtracker.dto.TimePeriodDto;
import com.rentinhand.rihtracker.dto.requests.project.ProjectDataRequest;
import com.rentinhand.rihtracker.dto.responses.dashboard.DashboardAllResponse;
import com.rentinhand.rihtracker.dto.responses.project.ProjectResponse;
import com.rentinhand.rihtracker.dto.responses.task.TaskShortResponse;
import com.rentinhand.rihtracker.dto.task.CurrentTaskDto;
import com.rentinhand.rihtracker.dto.task.TrackedTaskDto;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.TimeEntry;
import com.rentinhand.rihtracker.exceptions.ModelNotFoundException;
import com.rentinhand.rihtracker.repos.ProjectRepository;
import com.rentinhand.rihtracker.repos.TaskRepository;
import com.rentinhand.rihtracker.repos.TimeEntryRepository;
import com.rentinhand.rihtracker.services.DashboardService;
import com.rentinhand.rihtracker.services.ProjectService;
import com.rentinhand.rihtracker.services.TaskTypeService;
import com.rentinhand.rihtracker.services.TimeEntryService;
import com.rentinhand.rihtracker.services.auth.SecurityWorkspace;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
