package com.fyordo.shatback.dto.responses.timeEntry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeEntryShortResponse {
    private Long id;

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;

    private String description;
}
