package com.rentinhand.rihtracker.dto.responses.scrumColumn;

import com.rentinhand.rihtracker.dto.responses.task.TaskResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrumColumnResponse {
    protected Long id;
    protected String title;
    protected String color;
    protected List<TaskResponse> tasks;
}
