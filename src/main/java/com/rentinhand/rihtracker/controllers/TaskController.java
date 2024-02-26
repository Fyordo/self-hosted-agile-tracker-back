package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.responses.ListResponse;
import com.rentinhand.rihtracker.dto.responses.scrumColumn.ScrumColumnResponse;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.exceptions.ModelNotFoundException;
import com.rentinhand.rihtracker.services.ProjectService;
import com.rentinhand.rihtracker.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/{projectId}/task")
public class TaskController extends BaseController {
    private final ProjectService projectService;
    private final TaskService taskService;

    @GetMapping()
    public ResponseEntity<ListResponse<ScrumColumnResponse>> getTasksWithColumn(
            @PathVariable Long projectId
    ){
        List<ScrumColumn> columns = taskService.getProjectTasksByColumns(
                projectService.findById(projectId).orElseThrow(ModelNotFoundException::new)
        ).stream().toList();

        List<ScrumColumnResponse> mappedResult = columns.stream().map((element) -> mapper.map(element, ScrumColumnResponse.class)).toList();
        return ResponseEntity.ok(new ListResponse<>(mappedResult));
    }
}
