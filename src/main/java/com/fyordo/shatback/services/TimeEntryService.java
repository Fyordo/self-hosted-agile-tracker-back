package com.fyordo.shatback.services;

import com.fyordo.shatback.dto.requests.timeEntry.TimeEntryCreateRequest;
import com.fyordo.shatback.dto.requests.timeEntry.TimeEntryUpdateRequest;
import com.fyordo.shatback.entities.Project;
import com.fyordo.shatback.entities.Task;
import com.fyordo.shatback.entities.TimeEntry;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TimeEntryService {
    List<TimeEntry> findAll();
    List<TimeEntry> findAllForCurrentWeek();
    Optional<TimeEntry> findById(Long timeEntryId);
    Optional<TimeEntry> getCurrentTimeEntry();
    TimeEntry startTimeEntry(Task task, TimeEntryCreateRequest request);
    List<TimeEntry> getEntriesByDateAndProjectId(LocalDate date, Project project);
    TimeEntry updateTimeEntry(TimeEntry timeEntry, TimeEntryUpdateRequest timeEntryData);
    TimeEntry endTimeEntry(Long timeEntryId);
    void deleteTimeEntry(TimeEntry timeEntry);
}
