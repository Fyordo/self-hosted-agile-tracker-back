package com.rentinhand.rihtracker.services.implementations;

import com.rentinhand.rihtracker.builders.TaskBuilder;
import com.rentinhand.rihtracker.dto.requests.task.TaskCreateRequest;
import com.rentinhand.rihtracker.dto.requests.task.TaskUpdateRequest;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.User;
import com.rentinhand.rihtracker.exceptions.ModelNotFoundException;
import com.rentinhand.rihtracker.repos.TaskRepository;
import com.rentinhand.rihtracker.services.ScrumColumnService;
import com.rentinhand.rihtracker.services.TaskService;
import com.rentinhand.rihtracker.services.TaskTypeService;
import com.rentinhand.rihtracker.services.UserService;
import com.rentinhand.rihtracker.services.auth.SecurityWorkspace;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final SecurityWorkspace securityWorkspace;

    private final TaskRepository taskRepository;
    private final ScrumColumnService scrumColumnService;
    private final UserService userService;
    private final TaskTypeService taskTypeService;
    private ModelMapper mapper = new ModelMapper();

    @Override
    public Collection<Task> findAll(Map<String, String> filter) {
        Collection<Task> tasks = taskRepository.findAll();
        return filter(tasks, filter);
    }

    @Override
    public Collection<Task> getProjectTasks(Project project) {
        Set<Task> tasks = new HashSet<>();
        project.getColumns().forEach((ScrumColumn column) -> tasks.addAll(column.getTasks()));

        return tasks;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task createTask(TaskCreateRequest taskData, ScrumColumn scrumColumn) {
        Task task = mapper.map(taskData, Task.class);
        task.setScrumColumn(scrumColumn);
        task.setCreatedUser(securityWorkspace.getAuthUser());
        taskRepository.save(task);

        return task;
    }

    @Override
    public Task updateTask(Task task, TaskUpdateRequest taskData) {
        TaskBuilder taskBuilder = taskDataToBuilder(taskData);

        taskRepository.updateTask(
                Optional.of(taskBuilder.getTitle()).orElse(task.getTitle()),
                Optional.of(taskBuilder.getDescription()).orElse(task.getDescription()),
                Optional.of(taskBuilder.getDeadline()).orElse(task.getDeadline()),
                Optional.of(taskBuilder.getTaskType()).orElse(task.getTaskType()),
                Optional.of(taskBuilder.getScrumColumn()).orElse(task.getScrumColumn()),
                Optional.of(taskBuilder.getMaintainer()).orElse(task.getMaintainer()),
                task.getId()
        );

        return task;
    }

    @Override
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public Task addUserToTask(Task task, User user) {
        task.getUsers().add(user);

        taskRepository.save(task);

        return task;
    }

    @Override
    public Task changeTaskColumn(Task task, ScrumColumn scrumColumn) {
        task.setScrumColumn(scrumColumn);

        taskRepository.save(task);

        return task;
    }

    private TaskBuilder taskDataToBuilder(TaskUpdateRequest taskData) {
        TaskBuilder taskBuilder = new TaskBuilder();
        taskBuilder.setTitle(taskData.getTitle());
        taskBuilder.setDescription(taskData.getDescription());
        taskBuilder.setDeadline(taskData.getDeadline());
        taskBuilder.setTaskType(taskTypeService.findById(taskData.getTaskTypeId()).orElseThrow(ModelNotFoundException::new));
        taskBuilder.setMaintainer(userService.findById(taskData.getMaintainerUserId()).orElseThrow(ModelNotFoundException::new));
        taskBuilder.setCreatedUser(userService.findById(taskData.getCreatedUserId()).orElseThrow(ModelNotFoundException::new));

        return taskBuilder;
    }

    private List<Task> filter(Collection<Task> collection, Map<String, String> filter) {
        return collection.stream().filter(
                (Task task) -> {
                    AtomicBoolean result = new AtomicBoolean(true);
                    filter.forEach((String key, String value) -> {
                        switch (key) {
                            case "search" -> result.set(result.get() &
                                    (task.getTitle().contains(value) || task.getDescription().contains(value))
                            );
                            case "title" -> result.set(
                                    result.get() &
                                            task.getTitle().equals(value)
                            );
                        }
                    });
                    return result.get();
                }
        ).collect(Collectors.toList());
    }
}
