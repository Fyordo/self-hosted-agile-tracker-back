package com.rentinhand.rihtracker.dto.taskType;

import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.TaskType;
import lombok.Data;

@Data
public class TaskTypeShortDto {
    private String title;
    private String color;


    public TaskTypeShortDto(TaskType taskType) {
    this.title = taskType.getTitle();
    this.color = taskType.getColor();

    }
}
