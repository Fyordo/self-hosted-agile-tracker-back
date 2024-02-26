package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.requests.scrumColumn.ScrumColumnDataRequest;
import com.rentinhand.rihtracker.dto.responses.ListResponse;
import com.rentinhand.rihtracker.dto.responses.scrumColumn.ScrumColumnResponse;
import com.rentinhand.rihtracker.dto.responses.scrumColumn.ScrumColumnShortResponse;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.exceptions.ModelNotFoundException;
import com.rentinhand.rihtracker.services.ProjectService;
import com.rentinhand.rihtracker.services.ScrumColumnService;
import com.rentinhand.rihtracker.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/{projectId}/column")
public class ScrumColumnController extends BaseController {
    private final ProjectService projectService;
    private final ScrumColumnService scrumColumnService;

    @GetMapping()
    public ResponseEntity<ListResponse<ScrumColumnShortResponse>> getColumnList(
            @PathVariable Long projectId
    ){
        List<ScrumColumnShortResponse> columns = scrumColumnService.findAllInProject(
                projectService.findById(projectId).orElseThrow(ModelNotFoundException::new)
        )
                .stream()
                .map((ScrumColumn project) -> mapper.map(project, ScrumColumnShortResponse.class))
                .toList()
                ;

        return ResponseEntity.ok(new ListResponse<>(columns));
    }

    @PostMapping()
    public ResponseEntity<ScrumColumnShortResponse> addColumnToProject(
            @PathVariable Long projectId,
            @RequestBody ScrumColumnDataRequest columnData
    ){
        ScrumColumn scrumColumn = scrumColumnService.createScrumColumn(columnData);

        return ResponseEntity.ok(mapper.map(scrumColumn, ScrumColumnShortResponse.class));
    }
}
