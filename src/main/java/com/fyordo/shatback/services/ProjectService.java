package com.fyordo.shatback.services;

import com.fyordo.shatback.dto.requests.project.ProjectDataRequest;
import com.fyordo.shatback.entities.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> findAll();
    Optional<Project> findById(Long projectId);
    Project createProject(ProjectDataRequest projectData);
    Project updateProject(Project project, ProjectDataRequest projectData);
    void deleteProject(Project project);
}
