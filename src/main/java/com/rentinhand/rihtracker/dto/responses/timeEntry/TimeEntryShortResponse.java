package com.rentinhand.rihtracker.dto.responses.timeEntry;

import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.User;
import jakarta.persistence.*;
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
