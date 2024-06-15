package com.fyordo.shatback.services;

import com.fyordo.shatback.dto.requests.task.TaskCreateRequest;
import com.fyordo.shatback.dto.requests.task.TaskUpdateRequest;
import com.fyordo.shatback.entities.Project;
import com.fyordo.shatback.entities.ScrumColumn;
import com.fyordo.shatback.entities.Task;
import com.fyordo.shatback.entities.User;
import com.fyordo.shatback.services.contracts.SearchService;

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

    Task addUserToTask(Long taskId, Long userId);
    Task changeTaskColumn(Task task, ScrumColumn scrumColumn);
}
