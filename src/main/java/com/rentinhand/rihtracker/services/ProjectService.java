package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.dto.requests.project.ProjectDataRequest;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.User;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> findAll();
    Optional<Project> findById(Long projectId);
    Project createProject(ProjectDataRequest projectData);
    Project updateProject(Project project, ProjectDataRequest projectData);
    boolean deleteProject(Project project);
}
