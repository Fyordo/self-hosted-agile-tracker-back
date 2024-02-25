package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.entities.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasks(Project project);
    List<ScrumColumn> getTasksByColumns(Project project);
}
