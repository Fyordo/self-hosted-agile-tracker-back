package com.fyordo.shatback.services;


import com.fyordo.shatback.dto.requests.taskType.TaskTypeDataRequest;
import com.fyordo.shatback.entities.TaskType;

import java.util.List;
import java.util.Optional;

public interface TaskTypeService {
    List<TaskType> findAll();
    Optional<TaskType> findById(Long taskTypeId);
    TaskType createTaskType(TaskTypeDataRequest taskTypeData);
    TaskType updateTaskType(TaskType taskType, TaskTypeDataRequest taskTypeData);
    void deleteTaskType(TaskType taskType);
}
