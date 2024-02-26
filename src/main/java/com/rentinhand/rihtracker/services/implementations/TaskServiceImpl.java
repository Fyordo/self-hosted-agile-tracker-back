package com.rentinhand.rihtracker.services.implementations;

import com.rentinhand.rihtracker.dto.TaskDTO;
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
    public Collection<Task> getProjectTasks(Project project) {
        Set<Task> tasks = new HashSet<>();
        project.getColumns().forEach((ScrumColumn column) -> tasks.addAll(column.getTasks()));

        return tasks;
    }

    @Override
    public Optional<Task> findById(Long taskId) {
        return taskRepository.findById(taskId);
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
        TaskDTO taskDTO = taskDataToDTO(taskData);

        taskRepository.updateTask(
                Optional.of(taskDTO.getTitle()).orElse(task.getTitle()),
                Optional.of(taskDTO.getDescription()).orElse(task.getDescription()),
                Optional.of(taskDTO.getDeadline()).orElse(task.getDeadline()),
                Optional.of(taskDTO.getTaskType()).orElse(task.getTaskType()),
                Optional.of(taskDTO.getScrumColumn()).orElse(task.getScrumColumn()),
                Optional.of(taskDTO.getMaintainer()).orElse(task.getMaintainer()),
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

    private TaskDTO taskDataToDTO(TaskUpdateRequest taskData){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle(taskData.getTitle());
        taskDTO.setDescription(taskData.getDescription());
        taskDTO.setDeadline(taskData.getDeadline());
        taskDTO.setTaskType(taskTypeService.findById(taskData.getTaskTypeId()).orElseThrow(ModelNotFoundException::new));
        taskDTO.setMaintainer(userService.findById(taskData.getMaintainerUserId()).orElseThrow(ModelNotFoundException::new));
        taskDTO.setCreatedUser(userService.findById(taskData.getCreatedUserId()).orElseThrow(ModelNotFoundException::new));

        return taskDTO;
    }
}
