package com.rentinhand.rihtracker.dto.responses.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private User createdUser;

    private List<UserDto> users;


    public ProjectResponse(Project project) {
        this.id = project.getId();
        this.title = project.getTitle();
        this.avatar = project.getAvatar();
        this.createdUser = project.getCreatedUser();
        this.users = project.getUsers().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());;    }
}
