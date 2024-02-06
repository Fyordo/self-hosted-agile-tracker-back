package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class SecurityWorkspace {
    private final UserService userService;

    public User getAuthUser(){
        return userService.findById(getAuthUserId()).orElseThrow();
    }

    public Long getAuthUserId(){
        User unlinkedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return unlinkedUser.getId();
    }
}
