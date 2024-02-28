package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.dto.requests.task.TaskCreateRequest;
import com.rentinhand.rihtracker.dto.requests.task.TaskUpdateRequest;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.User;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface TaskService extends SearchService<Task> {
    Collection<Task> findAll(Map<String, String> filter);
    Collection<Task> getProjectTasks(Project project);

    Optional<Task> findById(Long id);

    Task createTask(TaskCreateRequest taskData, ScrumColumn scrumColumn);

    Task updateTask(Task task, TaskUpdateRequest taskData);

    void deleteTask(Task task);

    Task addUserToTask(Task task, User user);
    Task changeTaskColumn(Task task, ScrumColumn scrumColumn);
}
