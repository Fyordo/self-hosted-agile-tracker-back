package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.requests.task.TaskCreateRequest;
import com.rentinhand.rihtracker.dto.requests.task.TaskUpdateRequest;
import com.rentinhand.rihtracker.dto.responses.task.TaskResponse;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.services.TaskService;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.UserAuth;
import com.rentinhand.rihtracker.utilities.SecurityWorkspace;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/task")
@UserAuth
public class TaskController extends BaseController{
    private final TaskService taskService;

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long taskId) {
        Optional<Task> task = taskService.findById(taskId);
        if (task.isPresent() && task.get().haveAccess(SecurityWorkspace.getAuthUser())) {
            return new ResponseEntity<>(new TaskResponse(task.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<Task> allTasks = taskService.findAll();
        if (!allTasks.isEmpty()) {
            List<TaskResponse> taskResponses = allTasks.stream()
                    .map(TaskResponse::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(taskResponses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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

    @PostMapping("{taskId}/add/user/{userId}")
    public ResponseEntity<TaskResponse> addUserToTask(@PathVariable Long taskId, @PathVariable Long userId) {
        Optional<Task> existingTask = taskService.findById(taskId);
        if (existingTask.isPresent()) {
            Task updatedTask = taskService.addUser(userId, existingTask.get());
            return new ResponseEntity<>(new TaskResponse(updatedTask), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
