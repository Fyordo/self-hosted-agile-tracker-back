package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.requests.task.taskType.TaskTypeCreateRequest;
import com.rentinhand.rihtracker.dto.requests.task.taskType.TaskTypeUpdateRequest;
import com.rentinhand.rihtracker.dto.responses.task.taskType.TaskTypeResponse;
import com.rentinhand.rihtracker.entities.TaskType;
import com.rentinhand.rihtracker.services.TaskTypeService;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.UserAuth;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/task-type")
@UserAuth
public class TaskTypeController extends BaseController{
    private final TaskTypeService taskTypeService;

    @GetMapping("/{taskTypeId}")
    public ResponseEntity<TaskTypeResponse> getTaskTypeById(@PathVariable Long taskTypeId) {
        Optional<TaskType> taskType = taskTypeService.findById(taskTypeId);
        return taskType.map(value -> new ResponseEntity<>(new TaskTypeResponse(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping
    public ResponseEntity<List<TaskTypeResponse>> getAllTaskTypes() {
        List<TaskType> allTaskTypes = taskTypeService.findAll();
        if (!allTaskTypes.isEmpty()) {
            List<TaskTypeResponse> taskTypeResponses = allTaskTypes.stream()
                    .map(TaskTypeResponse::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(taskTypeResponses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TaskTypeResponse> createTaskType(@RequestBody TaskTypeCreateRequest taskTypeCreateRequest) {
        TaskType createdTaskType = taskTypeService.createTaskType(taskTypeCreateRequest);
        return new ResponseEntity<>(new TaskTypeResponse(createdTaskType), HttpStatus.CREATED);
    }

    @PutMapping("/{taskTypeId}")
    public ResponseEntity<TaskTypeResponse> updateTaskType(@PathVariable Long taskTypeId,
                                                 @RequestBody TaskTypeUpdateRequest taskTypeUpdateRequest) {
        Optional<TaskType> existingTaskType = taskTypeService.findById(taskTypeId);
        if (existingTaskType.isPresent()) {
            TaskType updatedTaskType = taskTypeService.updateTaskType(existingTaskType.get(), taskTypeUpdateRequest);
            return new ResponseEntity<>(new TaskTypeResponse(updatedTaskType), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{taskTypeId}")
    public ResponseEntity<Void> deleteTaskType(@PathVariable Long taskTypeId) {
        Optional<TaskType> taskType = taskTypeService.findById(taskTypeId);
        if (taskType.isPresent()) {
            taskTypeService.deleteTaskType(taskType.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
