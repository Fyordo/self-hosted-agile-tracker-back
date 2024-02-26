package com.rentinhand.rihtracker.dto.requests.task;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TaskCreateRequest {
    protected String title;
}
