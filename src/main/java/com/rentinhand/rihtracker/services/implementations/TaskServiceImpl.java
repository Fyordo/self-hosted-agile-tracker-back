package com.rentinhand.rihtracker.services.implementations;

import com.rentinhand.rihtracker.dto.requests.task.TaskCreateRequest;
import com.rentinhand.rihtracker.dto.requests.task.TaskUpdateRequest;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.TaskType;
import com.rentinhand.rihtracker.entities.User;
import com.rentinhand.rihtracker.repos.*;
import com.rentinhand.rihtracker.services.TaskService;
import com.rentinhand.rihtracker.services.TaskService;
import com.rentinhand.rihtracker.utilities.SecurityWorkspace;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private TaskTypeRepository taskTypeRepository;
    private ProjectRepository projectRepository;
    private ScrumColumnRepository scrumColumnRepository;
    private UserRepository userRepository;

    @Override
    public Optional<Task> findById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    @Transactional
    public Task createTask(TaskCreateRequest taskData) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Task task = modelMapper.map(taskData, Task.class);
        User user = SecurityWorkspace.getAuthUser();
        task.setCreatedUser(user);
        taskRepository.save(task);

        return task;
    }

    @Override
    public Task updateTask(Task task, TaskUpdateRequest taskData) {
        task.setTitle(taskData.getTitle());
        task.setDescription(taskData.getDescription());
        task.setDeadline(taskData.getDeadline());
        taskRepository.save(task);
        return task;
    }

    public Task setTaskType(Long taskTypeId, Task task) {
        taskTypeRepository.findById(taskTypeId).ifPresent(task::setTaskType);
        return task;
    }
    public Task setProject(Long projectId, Task task) {
        projectRepository.findById(projectId).ifPresent(task::setProject);
        return task;
    }
    public Task setScrumColumn(Long scrumColumnId, Task task) {
        scrumColumnRepository.findById(scrumColumnId).ifPresent(task::setScrumColumn);
        return task;
    }

    public Task setMaintainer(Long maintainerId, Task task) {
        userRepository.findById(maintainerId).ifPresent(task::setMaintainer);
        return task;
    }


    @Override
    public boolean deleteTask(Task task) {
        taskRepository.delete(task);
        return false;
    }
}
