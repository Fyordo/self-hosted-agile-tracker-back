package com.fyordo.shatback.controllers;

import com.fyordo.shatback.dto.requests.project.ProjectDataRequest;
import com.fyordo.shatback.dto.requests.scrumColumn.ScrumColumnDataRequest;
import com.fyordo.shatback.dto.responses.ListResponse;
import com.fyordo.shatback.dto.responses.project.ProjectDetailResponse;
import com.fyordo.shatback.dto.responses.project.ProjectResponse;
import com.fyordo.shatback.dto.responses.scrumColumn.ScrumColumnShortResponse;
import com.fyordo.shatback.entities.Project;
import com.fyordo.shatback.entities.ScrumColumn;
import com.fyordo.shatback.exceptions.ModelNotFoundException;
import com.fyordo.shatback.services.ProjectService;
import com.fyordo.shatback.services.ScrumColumnService;
import com.fyordo.shatback.utilities.AuthorityAnnotations.DirectorAuth;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
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
                .map((ScrumColumn column) -> mapper.map(column, ScrumColumnShortResponse.class))
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
