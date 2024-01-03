package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.dto.requests.auth.AuthEmailRequest;
import com.rentinhand.rihtracker.dto.requests.user.UserCreateRequest;
import com.rentinhand.rihtracker.dto.responses.auth.AuthResponse;
import com.rentinhand.rihtracker.services.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController extends BaseController{
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
