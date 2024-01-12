package com.rentinhand.rihtracker.dto.responses.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rentinhand.rihtracker.dto.requests.task.TaskDataRequest;
import com.rentinhand.rihtracker.dto.task.TaskDto;
import com.rentinhand.rihtracker.dto.user.UserDto;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    private Long id;

    private String title;

    private String avatar;

    private UserDto createdUser;

    private List<UserDto> users;

    private List<TaskDto> tasks;

    public ProjectResponse(Project project) {
        this.id = project.getId();
        this.title = project.getTitle();
        this.avatar = project.getAvatar();
        this.createdUser = new UserDto(project.getCreatedUser());
        this.users = project.getUsers().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        this.tasks = project.getTasks().stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
    }
}
