package com.rentinhand.rihtracker.utilities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    DIRECTOR;
    @Override
    public String getAuthority() {
        return name();
    }
}