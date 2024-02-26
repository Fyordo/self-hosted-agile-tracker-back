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
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.AdminAuth;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.DirectorAuth;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.NonUserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/column")
@DirectorAuth
public class ScrumColumnController extends BaseController {
    private final ProjectService projectService;
    private final ScrumColumnService scrumColumnService;

    @GetMapping("/{columnId}")
    public ResponseEntity<ScrumColumnResponse> getColumn(
            @PathVariable Long columnId
    ){
        ScrumColumn scrumColumn = scrumColumnService.findById(columnId).orElseThrow(ModelNotFoundException::new);

        return ResponseEntity.ok(mapper.map(scrumColumn, ScrumColumnResponse.class));
    }

    @PutMapping("/{columnId}")
    @NonUserAuth
    public ResponseEntity<ScrumColumnShortResponse> updateColumn(
            @PathVariable Long columnId,
            @RequestBody ScrumColumnDataRequest columnData
    ){
        ScrumColumn scrumColumn = scrumColumnService.updateScrumColumn(
                scrumColumnService.findById(columnId).orElseThrow(ModelNotFoundException::new),
                columnData
        );

        return ResponseEntity.ok(mapper.map(scrumColumn, ScrumColumnShortResponse.class));
    }

    @DeleteMapping("/{columnId}")
    @NonUserAuth
    public ResponseEntity<?> deleteColumn(
            @PathVariable Long columnId
    ){
        scrumColumnService.deleteScrumColumn(
                scrumColumnService.findById(columnId).orElseThrow(ModelNotFoundException::new)
        );
        return ResponseEntity.ok(null);
    }
}
