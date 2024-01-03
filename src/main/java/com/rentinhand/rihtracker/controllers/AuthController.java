package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.requests.user.AuthEmailRequest;
import com.rentinhand.rihtracker.dto.requests.user.UserCreateRequest;
import com.rentinhand.rihtracker.dto.responses.user.AuthResponse;
import com.rentinhand.rihtracker.dto.responses.user.UserResponse;
import com.rentinhand.rihtracker.services.UserService;
import com.rentinhand.rihtracker.services.auth.AuthService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {
    private UserService userService;
    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody
            UserCreateRequest data
    ) {
        return ResponseEntity.ok(service.register(data));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateEmail(
            @RequestBody AuthEmailRequest data
    ) {
        return ResponseEntity.ok(service.authenticateEmail(data));
    }
}
