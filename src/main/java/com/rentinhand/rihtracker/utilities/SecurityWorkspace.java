package com.rentinhand.rihtracker.utilities;

import com.rentinhand.rihtracker.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityWorkspace {

    public static User getAuthUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Long getAuthUserId(){
        return getAuthUser().getId();
    }
}
