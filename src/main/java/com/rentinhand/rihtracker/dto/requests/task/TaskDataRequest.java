package com.rentinhand.rihtracker.dto.requests.task;

import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.entities.TaskType;
import com.rentinhand.rihtracker.entities.User;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TaskDataRequest {
    private String title;

    private String description;

    private Timestamp deadline;

    private TaskType taskType;

    private Long scrumColumnId;

    private Long createdUserId;

    private Long maintainerUserId;
}
