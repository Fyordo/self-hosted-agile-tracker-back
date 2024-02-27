package com.rentinhand.rihtracker.dto;

import com.rentinhand.rihtracker.entities.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
public class TimeEntryDTO {
    private Long id;
    private String description;

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;

    private Task task;

    private User user;
}
