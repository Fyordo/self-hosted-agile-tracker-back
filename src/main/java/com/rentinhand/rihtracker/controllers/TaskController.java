package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.requests.task.TaskUpdateRequest;
import com.rentinhand.rihtracker.dto.requests.timeEntry.TimeEntryCreateRequest;
import com.rentinhand.rihtracker.dto.responses.task.TaskResponse;
import com.rentinhand.rihtracker.dto.responses.timeEntry.TimeEntryResponse;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.TimeEntry;
import com.rentinhand.rihtracker.exceptions.ModelNotFoundException;
import com.rentinhand.rihtracker.services.TaskService;
import com.rentinhand.rihtracker.services.TimeEntryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/task/{taskId}")
public class TaskController extends BaseController {
    private final TaskService taskService;
    private final TimeEntryService timeEntryService;

    @GetMapping()
    public ResponseEntity<TaskResponse> getTask(
            @PathVariable Long taskId
    ) {
        Task task = taskService.findById(taskId).orElseThrow(ModelNotFoundException::new);

        return ResponseEntity.ok(mapper.map(task, TaskResponse.class));
    }

    @PutMapping()
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long taskId,
            TaskUpdateRequest request
    ) {
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

    // TIME ENTRY

    @PostMapping("start-entry")
    public ResponseEntity<TimeEntryResponse> startTimeEntryInTask(
            @PathVariable Long taskId,
            @RequestBody TimeEntryCreateRequest request
    ) {
        TimeEntry timeEntry = timeEntryService.startTimeEntry(
                taskService.findById(taskId).orElseThrow(ModelNotFoundException::new),
                request
        );

        return ResponseEntity.ok(mapper.map(timeEntry, TimeEntryResponse.class));
    }
}
