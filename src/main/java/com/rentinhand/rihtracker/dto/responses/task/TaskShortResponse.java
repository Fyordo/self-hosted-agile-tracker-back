package com.rentinhand.rihtracker.dto.responses.task;

import com.rentinhand.rihtracker.dto.responses.user.UserResponse;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.TaskType;
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

    public TaskShortResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.deadline = task.getDeadline();
        this.taskType = task.getTaskType();

    }
}
