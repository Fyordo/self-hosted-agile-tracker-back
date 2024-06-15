package com.fyordo.shatback.services.auth;

import com.fyordo.shatback.dto.requests.auth.AuthEmailRequest;
import com.fyordo.shatback.dto.requests.user.UserCreateRequest;
import com.fyordo.shatback.dto.responses.auth.AuthResponse;
import com.fyordo.shatback.entities.User;
import com.fyordo.shatback.exceptions.WrongCredentialsException;
import com.fyordo.shatback.repos.UserRepository;
import com.fyordo.shatback.utilities.Role;
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
