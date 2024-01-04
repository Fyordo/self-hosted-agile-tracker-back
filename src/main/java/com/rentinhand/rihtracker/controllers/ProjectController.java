package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.requests.project.ProjectCreateRequest;
import com.rentinhand.rihtracker.dto.requests.project.ProjectUpdateRequest;
import com.rentinhand.rihtracker.dto.requests.user.UserUpdateRequest;
import com.rentinhand.rihtracker.dto.responses.user.UserResponse;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.User;
import com.rentinhand.rihtracker.exceptions.ModelNotFoundException;
import com.rentinhand.rihtracker.services.ProjectService;
import com.rentinhand.rihtracker.services.UserService;
import com.rentinhand.rihtracker.utilities.AuthorityAnnotations.UserAuth;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/project")
@UserAuth
public class ProjectController extends BaseController{
    private final ProjectService projectService;

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long projectId) {
        Optional<Project> project = projectService.findById(projectId);
        return project.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody ProjectCreateRequest projectCreateRequest) {
        Project createdProject = projectService.createProject(projectCreateRequest);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Long projectId,
                                                 @RequestBody ProjectUpdateRequest projectUpdateRequest) {
        Optional<Project> existingProject = projectService.findById(projectId);
        if (existingProject.isPresent()) {
            Project updatedProject = projectService.updateProject(existingProject.get(), projectUpdateRequest);
            return new ResponseEntity<>(updatedProject, HttpStatus.OK);
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
}
