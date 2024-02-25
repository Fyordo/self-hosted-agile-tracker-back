package com.rentinhand.rihtracker.dto.responses.task;

import com.rentinhand.rihtracker.dto.responses.user.UserResponse;
import com.rentinhand.rihtracker.entities.TaskType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    protected Long id;
    protected String title;
    protected Timestamp deadline;
    protected TaskType taskType;
    protected UserResponse maintainer;
}
