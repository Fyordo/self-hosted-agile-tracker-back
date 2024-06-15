package com.fyordo.shatback.dto.requests.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserCreateRequest extends UserDataRequest{
    private String password;
}
