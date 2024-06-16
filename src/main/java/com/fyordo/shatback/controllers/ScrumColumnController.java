package com.fyordo.shatback.controllers;

import com.fyordo.shatback.dto.requests.scrumColumn.ScrumColumnDataRequest;
import com.fyordo.shatback.dto.requests.task.TaskCreateRequest;
import com.fyordo.shatback.dto.responses.ListResponse;
import com.fyordo.shatback.dto.responses.scrumColumn.ScrumColumnResponse;
import com.fyordo.shatback.dto.responses.scrumColumn.ScrumColumnShortResponse;
import com.fyordo.shatback.dto.responses.task.TaskResponse;
import com.fyordo.shatback.dto.responses.task.TaskShortResponse;
import com.fyordo.shatback.entities.ScrumColumn;
import com.fyordo.shatback.entities.Task;
import com.fyordo.shatback.exceptions.ModelNotFoundException;
import com.fyordo.shatback.services.ScrumColumnService;
import com.fyordo.shatback.services.TaskService;
import com.fyordo.shatback.utilities.AuthorityAnnotations.DirectorAuth;
import com.fyordo.shatback.utilities.AuthorityAnnotations.NonUserAuth;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/column/{columnId}")
public class ScrumColumnController extends BaseController {
    private final ScrumColumnService scrumColumnService;
    private final TaskService taskService;

    @GetMapping("")
    public ResponseEntity<ScrumColumnResponse> getColumn(
            @PathVariable Long columnId
    ) {
        ScrumColumn scrumColumn = scrumColumnService.findById(columnId).orElseThrow(ModelNotFoundException::new);

        return ResponseEntity.ok(mapper.map(scrumColumn, ScrumColumnResponse.class));
    }

    @PutMapping("")
    @NonUserAuth
    public ResponseEntity<ScrumColumnShortResponse> updateColumn(
            @PathVariable Long columnId,
            @RequestBody ScrumColumnDataRequest columnData
    ) {
        ScrumColumn scrumColumn = scrumColumnService.updateScrumColumn(
                scrumColumnService.findById(columnId).orElseThrow(ModelNotFoundException::new),
                columnData
        );

        return ResponseEntity.ok(mapper.map(scrumColumn, ScrumColumnShortResponse.class));
    }

    @DeleteMapping("")
    @NonUserAuth
    public ResponseEntity<?> deleteColumn(
            @PathVariable Long columnId
    ) {
        scrumColumnService.deleteScrumColumn(
                scrumColumnService.findById(columnId).orElseThrow(ModelNotFoundException::new)
        );
        return ResponseEntity.ok(null);
    }

    @GetMapping("/tasks")
    public ResponseEntity<ListResponse<TaskShortResponse>> getColumnTasks(
            @PathVariable Long columnId
    ) {

        List<TaskShortResponse> columns = scrumColumnService.findById(columnId).orElseThrow(ModelNotFoundException::new)
                .getTasks()
                .stream()
                .map((Task task) -> mapper.map(task, TaskShortResponse.class))
                .toList();

        return ResponseEntity.ok(new ListResponse<>(columns));
    }

    @PostMapping("/add-task")
    public ResponseEntity<TaskResponse> addTaskToColumn(
            @PathVariable Long columnId,
            @RequestBody TaskCreateRequest request
    ) {
        Task task = taskService.createTask(
                request,
                scrumColumnService.findById(columnId).orElseThrow(ModelNotFoundException::new)
        );

        return ResponseEntity.ok(mapper.map(task, TaskResponse.class));
    }
}
