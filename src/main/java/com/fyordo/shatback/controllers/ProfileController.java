package com.fyordo.shatback.controllers;

import com.fyordo.shatback.dto.requests.user.UserUpdateRequest;
import com.fyordo.shatback.dto.responses.user.UserResponse;
import com.fyordo.shatback.entities.User;
import com.fyordo.shatback.exceptions.ModelNotFoundException;
import com.fyordo.shatback.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController extends BaseController{
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getProfile(@PathVariable Long userId) {
        User user = userService.findById(userId).orElseThrow(ModelNotFoundException::new);

        ModelMapper modelMapper = new ModelMapper();
        UserResponse response = modelMapper.map(user, UserResponse.class);
        response.setUsername(user.getActualUsername());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateProfile(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequest request
    ) {
        User user = userService.findById(userId).orElseThrow(ModelNotFoundException::new);

        user = userService.updateUser(user, request);

        ModelMapper modelMapper = new ModelMapper();
        UserResponse response = modelMapper.map(user, UserResponse.class);
        response.setUsername(user.getActualUsername());
        return ResponseEntity.ok(response);
    }
}
