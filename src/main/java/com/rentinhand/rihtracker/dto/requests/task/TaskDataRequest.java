package com.rentinhand.rihtracker.dto.requests.task;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public abstract class TaskDataRequest {
    private String title;
    private String description;
    private Timestamp deadline;

    private Long taskTypeId;
    private Long projectId;
    private Long scrumColumnId;
    private Long maintainerId;
}
