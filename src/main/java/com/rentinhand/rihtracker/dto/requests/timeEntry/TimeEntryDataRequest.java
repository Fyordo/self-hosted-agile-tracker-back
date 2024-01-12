package com.rentinhand.rihtracker.dto.requests.timeEntry;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public abstract class TimeEntryDataRequest {
    private String description;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private Long taskId;
    private Long userId;
}
