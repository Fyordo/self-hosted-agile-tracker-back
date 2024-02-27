package com.rentinhand.rihtracker.dto.requests.timeEntry;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeEntryDataRequest {
    private Long taskId;
    private String description;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
}
