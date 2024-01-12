package com.rentinhand.rihtracker.dto.responses.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    private Long id;

    private String title;

    private String avatar;

    private User createdUser;

    private Set<User> users;


    public ProjectResponse(Project project) {
        this.id = project.getId();
        this.title = project.getTitle();
        this.avatar = project.getAvatar();
        this.createdUser = project.getCreatedUser();
        this.users = project.getUsers();
    }
}
