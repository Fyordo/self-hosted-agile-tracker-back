package com.rentinhand.rihtracker.services.implementations;


import com.rentinhand.rihtracker.dto.requests.project.ProjectCreateRequest;
import com.rentinhand.rihtracker.dto.requests.project.ProjectUpdateRequest;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.repos.ProjectRepository;
import com.rentinhand.rihtracker.services.ProjectService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository ProjectRepository;

    @Override
    public Optional<Project> findById(Long ProjectId) {
        return ProjectRepository.findById(ProjectId);
    }

    @Override
    public Project createProject(ProjectCreateRequest ProjectData) {
        ModelMapper modelMapper = new ModelMapper();
        Project Project = modelMapper.map(ProjectData, Project.class);
        ProjectRepository.save(Project);
        return Project;
    }

    @Override
    public Project updateProject(Project Project, ProjectUpdateRequest ProjectData) {
        Project.setTitle(ProjectData.getTitle());
        Project.setAvatar(ProjectData.getAvatar());
        Project.setAvatar(ProjectData.getAvatar());
        ProjectRepository.save(Project);
        return Project;
    }

    @Override
    public boolean deleteProject(Project Project) {
        ProjectRepository.delete(Project);
        return false;
    }
}
