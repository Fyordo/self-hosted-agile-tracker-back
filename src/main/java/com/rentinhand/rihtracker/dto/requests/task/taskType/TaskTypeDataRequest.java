package com.rentinhand.rihtracker.dto.requests.task.taskType;

import lombok.Data;

import java.util.Set;

@Data
public abstract class TaskTypeDataRequest {
    private String title;
    private String color;
}
