package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.dto.requests.task.TaskDataRequest;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    Collection<Task> getProjectTasks(Project project);

    Optional<Task> findById(Long taskId);

    Task createTask(TaskDataRequest taskData);

    Task updateTask(Task task, TaskDataRequest taskData);

    void deleteTask(Task task);

    Task addUserToTask(Task task, User user);
    Task changeTaskColumn(Task task, ScrumColumn scrumColumn);
}
