package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.dto.requests.timeEntry.TimeEntryDataRequest;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.TimeEntry;

import java.util.List;
import java.util.Optional;

public interface TimeEntryService {
    List<TimeEntry> findAll();
    Optional<TimeEntry> findById(Long timeEntryId);
    TimeEntry startTimeEntry(Task task, String description);
    TimeEntry updateTimeEntry(TimeEntry timeEntry, TimeEntryDataRequest timeEntryData);
    void deleteTimeEntry(TimeEntry timeEntry);
}
