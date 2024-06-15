package com.fyordo.shatback.dto.requests.user;

import lombok.Data;

@Data
public abstract class UserDataRequest {
    private String username;
    private String login;
    private String avatar;
}
