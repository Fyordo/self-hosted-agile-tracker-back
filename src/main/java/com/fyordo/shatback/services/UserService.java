package com.fyordo.shatback.services;

import com.fyordo.shatback.dto.requests.user.UserCreateRequest;
import com.fyordo.shatback.dto.requests.user.UserUpdateRequest;
import com.fyordo.shatback.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long userId);

    User createUser(UserCreateRequest userData);

    User updateUser(User user, UserUpdateRequest userData);

    void deleteUser(User user);
}
