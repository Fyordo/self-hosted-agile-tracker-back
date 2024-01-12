package com.rentinhand.rihtracker.services.implementations;

import com.rentinhand.rihtracker.dto.requests.project.ProjectCreateRequest;
import com.rentinhand.rihtracker.dto.requests.project.ProjectUpdateRequest;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.User;
import com.rentinhand.rihtracker.repos.ProjectRepository;
import com.rentinhand.rihtracker.repos.UserRepository;
import com.rentinhand.rihtracker.services.ProjectService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    @Override
    public Optional<Project> findById(Long projectId) {
        return projectRepository.findById(projectId);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project createProject(ProjectCreateRequest projectData) {
        ModelMapper modelMapper = new ModelMapper();
        Project project = modelMapper.map(projectData, Project.class);
        projectRepository.save(project);
        return project;
    }

    @Override
    public Project updateProject(Project project, ProjectUpdateRequest projectData) {
        project.setTitle(projectData.getTitle());
        project.setAvatar(projectData.getAvatar());
        projectRepository.save(project);
        return project;
    }

    @Override
    public boolean deleteProject(Project project) {
        projectRepository.delete(project);
        return false;
    }


    public Project addUser(Long userId, Project project) {
        Set<User> users = project.getUsers();
        userRepository.findById(userId).ifPresent(users::add);
        projectRepository.save(project);
        return project;
    }
}
