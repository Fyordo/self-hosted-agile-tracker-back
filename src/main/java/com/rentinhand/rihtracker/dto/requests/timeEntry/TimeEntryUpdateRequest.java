package com.rentinhand.rihtracker.dto.requests.timeEntry;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class TimeEntryUpdateRequest extends TimeEntryCreateRequest {
    protected Long taskId;
    protected LocalDateTime timeStart;
    protected LocalDateTime timeEnd;
}
