package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.requests.task.TaskUpdateRequest;
import com.rentinhand.rihtracker.dto.responses.ListResponse;
import com.rentinhand.rihtracker.dto.responses.project.ProjectResponse;
import com.rentinhand.rihtracker.dto.responses.scrumColumn.ScrumColumnResponse;
import com.rentinhand.rihtracker.dto.responses.task.TaskResponse;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.exceptions.ModelNotFoundException;
import com.rentinhand.rihtracker.services.ProjectService;
import com.rentinhand.rihtracker.services.ScrumColumnService;
import com.rentinhand.rihtracker.services.TaskService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/task/{taskId}")
public class TaskController extends BaseController {
    private final TaskService taskService;

    @GetMapping()
    public ResponseEntity<TaskResponse> getTask(
            @PathVariable Long taskId
    ){
        Task task = taskService.findById(taskId).orElseThrow(ModelNotFoundException::new);

        return ResponseEntity.ok(mapper.map(task, TaskResponse.class));
    }

    @PutMapping()
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long taskId,
            TaskUpdateRequest request
    ){
        Task task = taskService.findById(taskId).orElseThrow(ModelNotFoundException::new);
        task = taskService.updateTask(task, request);

        return ResponseEntity.ok(mapper.map(task, TaskResponse.class));
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteColumn(
            @PathVariable Long taskId
    ) {
        taskService.deleteTask(
                taskService.findById(taskId).orElseThrow(ModelNotFoundException::new)
        );
        return ResponseEntity.ok(null);
    }
}
