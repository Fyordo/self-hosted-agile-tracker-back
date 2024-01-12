package com.rentinhand.rihtracker.dto.responses.task.taskType;

import com.rentinhand.rihtracker.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskTypeResponse {
    private Long id;

    private String title;

    private String avatar;

    private User createdUser;

    private Set<User> users;
}
