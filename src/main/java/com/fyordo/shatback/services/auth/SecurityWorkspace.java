package com.fyordo.shatback.services.auth;

import com.fyordo.shatback.entities.User;
import com.fyordo.shatback.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityWorkspace {
    private final UserService userService;

    public User getAuthUser(){
        return userService.findById(getAuthUserId()).orElseThrow();
    }

    public Long getAuthUserId(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
}
