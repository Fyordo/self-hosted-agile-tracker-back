package com.fyordo.shatback.dto.task;

import com.fyordo.shatback.dto.responses.project.ProjectResponse;
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
