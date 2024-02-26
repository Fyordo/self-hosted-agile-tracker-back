package com.rentinhand.rihtracker.dto.requests.taskType;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TaskTypeDataRequest {
    private String title;

    private String color;
}
