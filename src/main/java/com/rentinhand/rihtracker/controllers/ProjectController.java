package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.requests.project.ProjectCreateRequest;
import com.rentinhand.rihtracker.dto.requests.project.ProjectUpdateRequest;
import com.rentinhand.rihtracker.dto.requests.user.UserUpdateRequest;
import com.rentinhand.rihtracker.dto.responses.project.ProjectResponse;
import com.rentinhand.rihtracker.dto.responses.task.TaskResponse;
import com.rentinhand.rihtracker.dto.responses.user.UserResponse;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.User;
import com.rentinhand.rihtracker.exceptions.ModelNotFoundException;
import com.rentinhand.rihtracker.services.ProjectService;
import com.rentinhand.rihtracker.services.UserService;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.UserAuth;
import com.rentinhand.rihtracker.utilities.SecurityWorkspace;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/project")
@UserAuth
public class ProjectController extends BaseController{
    private final ProjectService projectService;

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long projectId) {
        Optional<Project> project = projectService.findById(projectId);
        return project.map(value -> new ResponseEntity<>(new ProjectResponse(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        List<Project> allProjects = projectService.findAll();
        if (!allProjects.isEmpty()) {
            List<ProjectResponse> projectResponses = allProjects.stream()
                    .map(ProjectResponse::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(projectResponses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectCreateRequest projectCreateRequest) {
        Project createdProject = projectService.createProject(projectCreateRequest);
        return new ResponseEntity<>(new ProjectResponse(createdProject), HttpStatus.CREATED);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long projectId,
                                                 @RequestBody ProjectUpdateRequest projectUpdateRequest) {
        Optional<Project> existingProject = projectService.findById(projectId);
        if (existingProject.isPresent()) {
            Project updatedProject = projectService.updateProject(existingProject.get(), projectUpdateRequest);
            return new ResponseEntity<>(new ProjectResponse(updatedProject), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        Optional<Project> project = projectService.findById(projectId);
        if (project.isPresent()) {
            projectService.deleteProject(project.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{projectId}/add/user/{userId}")
    public ResponseEntity<ProjectResponse> addUserToProject(@PathVariable Long projectId, @PathVariable Long userId) {
        Optional<Project> existingTask = projectService.findById(projectId);
        if (existingTask.isPresent()) {
            Project updatedProject = projectService.addUser(userId, existingTask.get());
            return new ResponseEntity<>(new ProjectResponse(updatedProject), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{projectId}/tasks")
    public ResponseEntity<List<TaskResponse>> getProjectTasks(@PathVariable Long projectId) {
        Optional<Project> project = projectService.findById(projectId);
        if (project.isPresent() && project.get().haveAccess(SecurityWorkspace.getAuthUser())) {
            List<TaskResponse> projectResponses = project.get().getTasks().stream().map(TaskResponse::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(projectResponses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
