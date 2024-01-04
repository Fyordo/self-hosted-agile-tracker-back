package com.rentinhand.rihtracker.services.auth;

import com.rentinhand.rihtracker.dto.requests.auth.AuthEmailRequest;
import com.rentinhand.rihtracker.dto.requests.user.UserCreateRequest;
import com.rentinhand.rihtracker.dto.responses.auth.AuthResponse;
import com.rentinhand.rihtracker.exceptions.WrongCredentialsException;
import com.rentinhand.rihtracker.utilities.Role;
import com.rentinhand.rihtracker.entities.User;
import com.rentinhand.rihtracker.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(UserCreateRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .id(savedUser.getId())
                .build();
    }

    public AuthResponse authenticateEmail(AuthEmailRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        var user = repository.findByLogin(request.getLogin())
                .orElseThrow(WrongCredentialsException::new);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .id(user.getId())
                .build();
    }
}
