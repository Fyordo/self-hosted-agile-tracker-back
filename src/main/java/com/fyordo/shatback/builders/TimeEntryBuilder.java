package com.fyordo.shatback.builders;

import com.fyordo.shatback.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeEntryBuilder {
    private Long id;
    private String description;

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;

    private Task task;

    private User user;
}
