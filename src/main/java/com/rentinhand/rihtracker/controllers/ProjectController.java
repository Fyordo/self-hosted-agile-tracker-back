package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.requests.project.ProjectDataRequest;
import com.rentinhand.rihtracker.dto.requests.scrumColumn.ScrumColumnDataRequest;
import com.rentinhand.rihtracker.dto.responses.ListResponse;
import com.rentinhand.rihtracker.dto.responses.project.ProjectDetailResponse;
import com.rentinhand.rihtracker.dto.responses.project.ProjectResponse;
import com.rentinhand.rihtracker.dto.responses.scrumColumn.ScrumColumnShortResponse;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.exceptions.ModelNotFoundException;
import com.rentinhand.rihtracker.services.ProjectService;
import com.rentinhand.rihtracker.services.ScrumColumnService;
import com.rentinhand.rihtracker.services.TaskService;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.DirectorAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController extends BaseController {
    private final ProjectService projectService;
    private final ScrumColumnService scrumColumnService;

    @GetMapping("")
    public ResponseEntity<ListResponse<ProjectResponse>> getProjects(){
        List<ProjectResponse> response = projectService.findAll()
                .stream().map((Project project) -> mapper.map(project, ProjectResponse.class)).toList();
        return ResponseEntity.ok(new ListResponse<>(response));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDetailResponse> getProject(@PathVariable Long projectId){
        Project result = projectService.findById(projectId).orElseThrow(ModelNotFoundException::new);
        return ResponseEntity.ok(mapper.map(result, ProjectDetailResponse.class));
    }

    @PostMapping("")
    @DirectorAuth
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectDataRequest request){
        Project result = projectService.createProject(request);
        return ResponseEntity.ok(mapper.map(result, ProjectResponse.class));
    }

    @PutMapping("/{projectId}")
    @DirectorAuth
    public ResponseEntity<ProjectResponse> updateProject(@RequestBody ProjectDataRequest request, @PathVariable Long projectId){
        Project result = projectService.updateProject(
                projectService.findById(projectId).orElseThrow(ModelNotFoundException::new),
                request
        );
        return ResponseEntity.ok(mapper.map(result, ProjectResponse.class));
    }

    @DeleteMapping("/{projectId}")
    @DirectorAuth
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId){
        projectService.deleteProject(
                projectService.findById(projectId).orElseThrow(ModelNotFoundException::new)
        );
        return ResponseEntity.ok(null);
    }

    // ========== COLUMNS ===========

    @GetMapping("/{projectId}/columns")
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

    @PostMapping("/{projectId}/add-column")
    public ResponseEntity<ScrumColumnShortResponse> addColumnToProject(
            @PathVariable Long projectId,
            @RequestBody ScrumColumnDataRequest columnData
    ){
        ScrumColumn scrumColumn = scrumColumnService.createScrumColumn(
                columnData,
                projectService.findById(projectId).orElseThrow(ModelNotFoundException::new)
        );

        return ResponseEntity.ok(mapper.map(scrumColumn, ScrumColumnShortResponse.class));
    }
}
