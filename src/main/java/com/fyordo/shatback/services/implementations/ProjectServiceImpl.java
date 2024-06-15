package com.fyordo.shatback.services.implementations;

import com.fyordo.shatback.dto.requests.project.ProjectDataRequest;
import com.fyordo.shatback.entities.Project;
import com.fyordo.shatback.repos.ProjectRepository;
import com.fyordo.shatback.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private ModelMapper mapper = new ModelMapper();

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
        Project project = mapper.map(projectData, Project.class);
        projectRepository.save(project);
        return project;
    }

    @Override
    public Project updateProject(Project project, ProjectDataRequest projectData) {
        projectRepository.updateProject(
                Optional.of(projectData.getTitle()).orElse(project.getTitle()),
                Optional.of(projectData.getAvatar()).orElse(project.getAvatar()),
                project.getId()
        );
        return project;
    }

    @Override
    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }
}
