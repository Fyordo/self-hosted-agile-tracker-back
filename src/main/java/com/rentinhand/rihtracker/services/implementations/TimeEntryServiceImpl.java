package com.rentinhand.rihtracker.services.implementations;

import com.rentinhand.rihtracker.builders.TimeEntryBuilder;
import com.rentinhand.rihtracker.dto.requests.timeEntry.TimeEntryCreateRequest;
import com.rentinhand.rihtracker.dto.requests.timeEntry.TimeEntryUpdateRequest;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.TimeEntry;
import com.rentinhand.rihtracker.exceptions.ModelNotFoundException;
import com.rentinhand.rihtracker.repos.TimeEntryRepository;
import com.rentinhand.rihtracker.services.TaskService;
import com.rentinhand.rihtracker.services.TimeEntryService;
import com.rentinhand.rihtracker.services.auth.SecurityWorkspace;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimeEntryServiceImpl implements TimeEntryService {
    private final TimeEntryRepository timeEntryRepository;
    private final SecurityWorkspace securityWorkspace;
    private final TaskService taskService;
    private ModelMapper mapper = new ModelMapper();

    @Override
    public List<TimeEntry> findAll() {
        return timeEntryRepository.findAll();
    }

    @Override
    public Optional<TimeEntry> findById(Long timeEntryId) {
        return timeEntryRepository.findById(timeEntryId);
    }

    @Override
    public TimeEntry startTimeEntry(Task task, TimeEntryCreateRequest request) {
        TimeEntry timeEntry = new TimeEntry(
                null,
                LocalDateTime.now(),
                null,
                request.getDescription(),
                task,
                securityWorkspace.getAuthUser()
        );
        timeEntryRepository.save(timeEntry);

        return timeEntry;
    }

    @Override
    public TimeEntry updateTimeEntry(TimeEntry timeEntry, TimeEntryUpdateRequest timeEntryData) {
        TimeEntryBuilder timeEntryBuilder = requestDataToBuilder(timeEntryData);
        timeEntryRepository.updateTimeEntry(
                Optional.of(timeEntryBuilder.getTimeStart()).orElse(timeEntry.getTimeStart()),
                Optional.of(timeEntryBuilder.getTimeEnd()).orElse(timeEntry.getTimeStart()),
                Optional.of(timeEntryBuilder.getDescription()).orElse(timeEntry.getDescription()),
                Optional.of(timeEntryBuilder.getTask()).orElse(timeEntry.getTask()),
                timeEntry.getId()
        );
        return timeEntry;
    }

    @Override
    public void deleteTimeEntry(TimeEntry timeEntry) {
        timeEntryRepository.delete(timeEntry);
    }

    private TimeEntryBuilder requestDataToBuilder(TimeEntryUpdateRequest timeEntryData){
        TimeEntryBuilder result = new TimeEntryBuilder();
        result.setDescription(timeEntryData.getDescription());
        result.setTimeEnd(timeEntryData.getTimeEnd());
        result.setTimeStart(timeEntryData.getTimeStart());
        result.setTask(taskService.findById(timeEntryData.getTaskId()).orElseThrow(ModelNotFoundException::new));

        return result;
    }
}
