package com.rentinhand.rihtracker.dto.responses.timeEntry;

import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.TimeEntry;
import com.rentinhand.rihtracker.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeEntryResponse {
    private Long id;

    private String description;

    private LocalDateTime timeEnd;

    private LocalDateTime timeStart;

    private User user;

    private Task task;

    public TimeEntryResponse(TimeEntry timeEntry) {
        this.id = timeEntry.getId();
        this.description = timeEntry.getDescription();
        this.timeEnd = timeEntry.getTimeEnd();
        this.timeStart = timeEntry.getTimeStart();
        this.user = timeEntry.getUser();
        this.task = timeEntry.getTask();
    }


}
