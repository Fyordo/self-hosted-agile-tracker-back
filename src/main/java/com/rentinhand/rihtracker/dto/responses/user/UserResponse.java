package com.rentinhand.rihtracker.dto.responses.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;

    private String login;
    private String username;

    @JsonIgnore
    private String password;

    private String avatar;
}
