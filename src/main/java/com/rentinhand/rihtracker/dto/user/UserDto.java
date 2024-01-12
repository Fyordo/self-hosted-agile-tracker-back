package com.rentinhand.rihtracker.dto.user;

import com.rentinhand.rihtracker.entities.User;
import com.rentinhand.rihtracker.services.UserService;
import lombok.Data;

@Data
public class UserDto {
    private String username;

    public UserDto(User user) {
        this.username = user.getUsername();
    }
}
