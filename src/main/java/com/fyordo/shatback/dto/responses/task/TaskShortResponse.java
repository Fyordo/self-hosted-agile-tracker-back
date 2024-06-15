package com.fyordo.shatback.dto.responses.task;

import com.fyordo.shatback.dto.responses.user.UserResponse;
import com.fyordo.shatback.entities.TaskType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskShortResponse {
    protected Long id;
    protected String title;
    protected Timestamp deadline;
    protected TaskType taskType;
    protected UserResponse maintainer;
}
