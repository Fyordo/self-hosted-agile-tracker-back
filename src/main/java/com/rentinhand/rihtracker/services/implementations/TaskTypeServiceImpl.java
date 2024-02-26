package com.rentinhand.rihtracker.services.implementations;

import com.rentinhand.rihtracker.dto.requests.taskType.TaskTypeDataRequest;
import com.rentinhand.rihtracker.entities.TaskType;
import com.rentinhand.rihtracker.repos.TaskTypeRepository;
import com.rentinhand.rihtracker.services.TaskTypeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskTypeServiceImpl implements TaskTypeService {
    private final TaskTypeRepository taskTypeRepository;
    private ModelMapper mapper = new ModelMapper();

    @Override
    public List<TaskType> findAll() {
        return taskTypeRepository.findAll();
    }

    @Override
    public Optional<TaskType> findById(Long taskTypeId) {
        return taskTypeRepository.findById(taskTypeId);
    }

    @Override
    public TaskType createTaskType(TaskTypeDataRequest taskTypeData) {
        TaskType taskType = mapper.map(taskTypeData, TaskType.class);
        taskTypeRepository.save(taskType);
        return taskType;
    }

    @Override
    public TaskType updateTaskType(TaskType taskType, TaskTypeDataRequest taskTypeData) {
        taskTypeRepository.updateTaskType(
                Optional.of(taskTypeData.getTitle()).orElse(taskType.getTitle()),
                Optional.of(taskTypeData.getColor()).orElse(taskType.getColor()),
                taskType.getId());
        return taskType;
    }

    @Override
    public void deleteTaskType(TaskType taskType) {
        taskTypeRepository.delete(taskType);
    }
}
