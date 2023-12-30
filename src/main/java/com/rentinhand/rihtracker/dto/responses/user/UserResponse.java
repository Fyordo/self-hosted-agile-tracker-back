package com.rentinhand.rihtracker.dto.responses.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;

    private String login;

    private String password;

    private String avatar;
}
