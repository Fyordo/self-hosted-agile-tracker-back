package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.requests.scrumColumn.ScrumColumnDataRequest;
import com.rentinhand.rihtracker.dto.requests.task.TaskCreateRequest;
import com.rentinhand.rihtracker.dto.responses.ListResponse;
import com.rentinhand.rihtracker.dto.responses.scrumColumn.ScrumColumnResponse;
import com.rentinhand.rihtracker.dto.responses.scrumColumn.ScrumColumnShortResponse;
import com.rentinhand.rihtracker.dto.responses.task.TaskResponse;
import com.rentinhand.rihtracker.dto.responses.task.TaskShortResponse;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.exceptions.ModelNotFoundException;
import com.rentinhand.rihtracker.services.ScrumColumnService;
import com.rentinhand.rihtracker.services.TaskService;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.DirectorAuth;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.NonUserAuth;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/column/{columnId}")
@DirectorAuth
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
