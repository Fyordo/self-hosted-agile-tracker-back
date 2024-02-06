package com.rentinhand.rihtracker.services.implementations;

import com.rentinhand.rihtracker.dto.requests.project.ProjectDataRequest;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.User;
import com.rentinhand.rihtracker.repos.ProjectRepository;
import com.rentinhand.rihtracker.services.ProjectService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> findById(Long projectId) {
        return projectRepository.findById(projectId);
    }

    @Override
    public Project createProject(ProjectDataRequest projectData) {
        ModelMapper modelMapper = new ModelMapper();
        Project project = modelMapper.map(projectData, Project.class);
        projectRepository.save(project);
        return project;
    }

    @Override
    public Project updateProject(Project project, ProjectDataRequest projectData) {
        project.setTitle(projectData.getTitle());
        project.setAvatar(projectData.getAvatar());
        return projectRepository.save(project);
    }

    @Override
    public boolean deleteProject(Project project) {
        projectRepository.delete(project);
        return false;
    }
}
