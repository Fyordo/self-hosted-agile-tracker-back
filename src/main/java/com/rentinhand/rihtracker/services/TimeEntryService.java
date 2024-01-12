package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.dto.requests.timeEntry.TimeEntryCreateRequest;
import com.rentinhand.rihtracker.dto.requests.timeEntry.TimeEntryUpdateRequest;
import com.rentinhand.rihtracker.entities.TimeEntry;

import java.util.Optional;

public interface TimeEntryService {
    Optional<TimeEntry> findById(Long timeEntryId);
    TimeEntry createTimeEntry(TimeEntryCreateRequest timeEntryData);
    TimeEntry updateTimeEntry(TimeEntry timeEntry, TimeEntryUpdateRequest timeEntryData);
    boolean deleteTimeEntry(TimeEntry timeEntry);
}
