package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.requests.user.UserUpdateRequest;
import com.rentinhand.rihtracker.dto.responses.user.UserResponse;
import com.rentinhand.rihtracker.entities.User;
import com.rentinhand.rihtracker.exceptions.ModelNotFoundException;
import com.rentinhand.rihtracker.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getProfile(@PathVariable Long userId) {
        User user = userService.findById(userId).orElseThrow(ModelNotFoundException::new);

        ModelMapper modelMapper = new ModelMapper();
        return ResponseEntity.ok(modelMapper.map(user, UserResponse.class));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateProfile(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequest request
    ) {
        User user = userService.findById(userId).orElseThrow(ModelNotFoundException::new);

        user = userService.updateUser(user, request);

        ModelMapper modelMapper = new ModelMapper();
        return ResponseEntity.ok(modelMapper.map(user, UserResponse.class));
    }
}
