package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.requests.project.ProjectDataRequest;
import com.rentinhand.rihtracker.dto.requests.user.UserUpdateRequest;
import com.rentinhand.rihtracker.dto.responses.ListResponse;
import com.rentinhand.rihtracker.dto.responses.project.ProjectDetailResponse;
import com.rentinhand.rihtracker.dto.responses.project.ProjectResponse;
import com.rentinhand.rihtracker.dto.responses.user.UserResponse;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.User;
import com.rentinhand.rihtracker.exceptions.ModelNotFoundException;
import com.rentinhand.rihtracker.services.ProjectService;
import com.rentinhand.rihtracker.services.UserService;
import com.rentinhand.rihtracker.services.auth.SecurityWorkspace;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.AdminAuth;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.Authorized;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.DirectorAuth;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.UserAuth;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
@Authorized
public class ProjectController extends BaseController {
    private final ProjectService projectService;

    @GetMapping("/")
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

    @PostMapping("/")
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
    public ResponseEntity<?> updateProject(@PathVariable Long projectId){
        projectService.deleteProject(
                projectService.findById(projectId).orElseThrow(ModelNotFoundException::new)
        );
        return ResponseEntity.ok(null);
    }
}
