package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.dto.requests.project.ProjectCreateRequest;
import com.rentinhand.rihtracker.dto.requests.project.ProjectUpdateRequest;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.Task;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Optional<Project> findById(Long projectId);
    public List<Project> findAll();
    Project createProject(ProjectCreateRequest projectData);
    Project updateProject(Project project, ProjectUpdateRequest projectData);
    boolean deleteProject(Project project);
}
