package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.dto.requests.user.UserCreateRequest;
import com.rentinhand.rihtracker.dto.requests.user.UserDataRequest;
import com.rentinhand.rihtracker.dto.requests.user.UserUpdateRequest;
import com.rentinhand.rihtracker.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long userId);

    User createUser(UserCreateRequest userData);

    User updateUser(User user, UserUpdateRequest userData);

    void deleteUser(User user);
}
