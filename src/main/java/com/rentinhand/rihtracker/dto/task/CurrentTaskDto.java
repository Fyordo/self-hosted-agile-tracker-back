package com.rentinhand.rihtracker.dto.task;

import com.rentinhand.rihtracker.dto.responses.project.ProjectResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentTaskDto {
    protected ProjectResponse project;
    protected TrackedTaskDto trackedTask;
}
