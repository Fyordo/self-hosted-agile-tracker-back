package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.requests.task.TaskCreateRequest;
import com.rentinhand.rihtracker.dto.requests.task.TaskUpdateRequest;
import com.rentinhand.rihtracker.dto.responses.task.TaskResponse;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.services.TaskService;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.UserAuth;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/task")
@UserAuth
public class TaskController extends BaseController{
    private final TaskService taskService;

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long taskId) {
        Optional<Task> task = taskService.findById(taskId);
        return task.map(value -> new ResponseEntity<>(new TaskResponse(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskCreateRequest taskCreateRequest) {
        Task createdTask = taskService.createTask(taskCreateRequest);
        return new ResponseEntity<>(new TaskResponse(createdTask), HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long taskId,
                                                 @RequestBody TaskUpdateRequest taskUpdateRequest) {
        Optional<Task> existingTask = taskService.findById(taskId);
        if (existingTask.isPresent()) {
            Task updatedTask = taskService.updateTask(existingTask.get(), taskUpdateRequest);
            return new ResponseEntity<>(new TaskResponse(updatedTask), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        Optional<Task> task = taskService.findById(taskId);
        if (task.isPresent()) {
            taskService.deleteTask(task.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
