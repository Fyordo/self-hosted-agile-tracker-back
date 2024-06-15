package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.dto.requests.timeEntry.TimeEntryCreateRequest;
import com.rentinhand.rihtracker.dto.requests.timeEntry.TimeEntryUpdateRequest;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.TimeEntry;

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
