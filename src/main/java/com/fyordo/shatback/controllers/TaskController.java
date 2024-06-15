package com.fyordo.shatback.controllers;

import com.fyordo.shatback.dto.requests.task.TaskUpdateRequest;
import com.fyordo.shatback.dto.requests.timeEntry.TimeEntryCreateRequest;
import com.fyordo.shatback.dto.responses.ListResponse;
import com.fyordo.shatback.dto.responses.task.TaskResponse;
import com.fyordo.shatback.dto.responses.timeEntry.TimeEntryResponse;
import com.fyordo.shatback.entities.Task;
import com.fyordo.shatback.entities.TimeEntry;
import com.fyordo.shatback.exceptions.ModelNotFoundException;
import com.fyordo.shatback.services.TaskService;
import com.fyordo.shatback.services.TimeEntryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController extends BaseController {
    private final TaskService taskService;
    private final TimeEntryService timeEntryService;

    @GetMapping()
    public ResponseEntity<ListResponse<TaskResponse>> getTasks(
            @RequestParam Map<String, String> filter
            ) {
        List<TaskResponse> tasks = taskService.findAll(filter)
                .stream().map((Task task) -> mapper.map(task, TaskResponse.class)).toList();

        return ResponseEntity.ok(new ListResponse<>(tasks));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTask(
            @PathVariable Long taskId
    ) {
        Task task = taskService.findById(taskId).orElseThrow(ModelNotFoundException::new);

        return ResponseEntity.ok(mapper.map(task, TaskResponse.class));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long taskId,
            TaskUpdateRequest request
    ) {
        Task task = taskService.findById(taskId).orElseThrow(ModelNotFoundException::new);
        task = taskService.updateTask(task, request);

        return ResponseEntity.ok(mapper.map(task, TaskResponse.class));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteColumn(
            @PathVariable Long taskId
    ) {
        taskService.deleteTask(
                taskService.findById(taskId).orElseThrow(ModelNotFoundException::new)
        );
        return ResponseEntity.ok(null);
    }

    // TIME ENTRY

    @PostMapping("/{taskId}/start-entry")
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
