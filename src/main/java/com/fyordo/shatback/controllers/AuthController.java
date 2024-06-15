package com.fyordo.shatback.controllers;

import com.fyordo.shatback.dto.requests.auth.AuthEmailRequest;
import com.fyordo.shatback.dto.requests.user.UserCreateRequest;
import com.fyordo.shatback.dto.responses.auth.AuthResponse;
import com.fyordo.shatback.services.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
