package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.dto.requests.project.ProjectCreateRequest;
import com.rentinhand.rihtracker.dto.requests.project.ProjectUpdateRequest;
import com.rentinhand.rihtracker.entities.Project;

import java.util.Optional;

public interface ProjectService {
    public Optional<Project> findById(Long ProjectId);
    public Project createProject(ProjectCreateRequest ProjectData);
    public Project updateProject(Project Project, ProjectUpdateRequest ProjectData);
    public boolean deleteProject(Project Project);
}
