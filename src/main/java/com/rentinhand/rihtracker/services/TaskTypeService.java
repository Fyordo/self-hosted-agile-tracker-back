package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.dto.requests.task.taskType.TaskTypeCreateRequest;
import com.rentinhand.rihtracker.dto.requests.task.taskType.TaskTypeUpdateRequest;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.TaskType;

import java.util.List;
import java.util.Optional;

public interface TaskTypeService {
    Optional<TaskType> findById(Long taskTypeId);
    List<TaskType> findAll();
    TaskType createTaskType(TaskTypeCreateRequest taskTypeData);
    TaskType updateTaskType(TaskType taskType, TaskTypeUpdateRequest taskTypeData);
    boolean deleteTaskType(TaskType taskType);
}
