package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.dto.requests.timeEntry.TimeEntryCreateRequest;
import com.rentinhand.rihtracker.dto.requests.timeEntry.TimeEntryUpdateRequest;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.TimeEntry;

import java.util.List;
import java.util.Optional;

public interface TimeEntryService {
    List<TimeEntry> findAll();
    Optional<TimeEntry> findById(Long timeEntryId);
    Optional<TimeEntry> getCurrentTimeEntry();
    TimeEntry startTimeEntry(Task task, TimeEntryCreateRequest request);
    TimeEntry updateTimeEntry(TimeEntry timeEntry, TimeEntryUpdateRequest timeEntryData);
    void deleteTimeEntry(TimeEntry timeEntry);
}
