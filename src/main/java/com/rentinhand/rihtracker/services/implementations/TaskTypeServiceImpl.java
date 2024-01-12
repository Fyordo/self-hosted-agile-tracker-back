package com.rentinhand.rihtracker.services.implementations;


import com.rentinhand.rihtracker.dto.requests.task.taskType.TaskTypeCreateRequest;
import com.rentinhand.rihtracker.dto.requests.task.taskType.TaskTypeUpdateRequest;
import com.rentinhand.rihtracker.entities.TaskType;
import com.rentinhand.rihtracker.entities.User;
import com.rentinhand.rihtracker.repos.*;
import com.rentinhand.rihtracker.services.TaskTypeService;
import com.rentinhand.rihtracker.utilities.SecurityWorkspace;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskTypeServiceImpl implements TaskTypeService {
    private TaskTypeRepository taskTypeRepository;

    @Override
    public Optional<TaskType> findById(Long taskTypeId) {
        return taskTypeRepository.findById(taskTypeId);
    }

    public List<TaskType> findAll() {
        return taskTypeRepository.findAll();
    }
    @Override
    @Transactional
    public TaskType createTaskType(TaskTypeCreateRequest taskTypeData) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TaskType taskType = modelMapper.map(taskTypeData, TaskType.class);
        taskTypeRepository.save(taskType);

        return taskType;
    }

    @Override
    public TaskType updateTaskType(TaskType taskType, TaskTypeUpdateRequest taskTypeData) {
        taskType.setTitle(taskTypeData.getTitle());
        taskType.setColor(taskTypeData.getColor());
        taskTypeRepository.save(taskType);
        return taskType;
    }

    @Override
    public boolean deleteTaskType(TaskType taskType) {
        taskTypeRepository.delete(taskType);
        return false;
    }
}
