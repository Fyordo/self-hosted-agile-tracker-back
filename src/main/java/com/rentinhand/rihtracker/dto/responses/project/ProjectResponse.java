package com.rentinhand.rihtracker.dto.responses.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
}
