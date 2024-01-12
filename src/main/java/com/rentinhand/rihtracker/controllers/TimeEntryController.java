package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.requests.timeEntry.TimeEntryCreateRequest;
import com.rentinhand.rihtracker.dto.requests.timeEntry.TimeEntryUpdateRequest;
import com.rentinhand.rihtracker.dto.responses.timeEntry.TimeEntryResponse;
import com.rentinhand.rihtracker.entities.TimeEntry;
import com.rentinhand.rihtracker.services.TimeEntryService;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.UserAuth;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/time-entry")
@UserAuth
public class TimeEntryController extends BaseController{
    private final TimeEntryService timeEntryService;

    @GetMapping("/{timeEntryId}")
    public ResponseEntity<TimeEntryResponse> getTimeEntryById(@PathVariable Long timeEntryId) {
        Optional<TimeEntry> timeEntry = timeEntryService.findById(timeEntryId);
        return timeEntry.map(value -> new ResponseEntity<>(new TimeEntryResponse(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TimeEntryResponse> createTimeEntry(@RequestBody TimeEntryCreateRequest timeEntryCreateRequest) {
        TimeEntry createdTimeEntry = timeEntryService.createTimeEntry(timeEntryCreateRequest);
        return new ResponseEntity<>(new TimeEntryResponse(createdTimeEntry), HttpStatus.CREATED);
    }

    @PutMapping("/{timeEntryId}")
    public ResponseEntity<TimeEntryResponse> updateTimeEntry(@PathVariable Long timeEntryId,
                                                 @RequestBody TimeEntryUpdateRequest timeEntryUpdateRequest) {
        Optional<TimeEntry> existingTimeEntry = timeEntryService.findById(timeEntryId);
        if (existingTimeEntry.isPresent()) {
            TimeEntry updatedTimeEntry = timeEntryService.updateTimeEntry(existingTimeEntry.get(), timeEntryUpdateRequest);
            return new ResponseEntity<>(new TimeEntryResponse(updatedTimeEntry), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{timeEntryId}")
    public ResponseEntity<Void> deleteTimeEntry(@PathVariable Long timeEntryId) {
        Optional<TimeEntry> timeEntry = timeEntryService.findById(timeEntryId);
        if (timeEntry.isPresent()) {
            timeEntryService.deleteTimeEntry(timeEntry.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
