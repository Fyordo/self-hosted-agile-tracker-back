package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.dto.requests.user.UserCreateRequest;
import com.rentinhand.rihtracker.dto.requests.user.UserDataRequest;
import com.rentinhand.rihtracker.dto.requests.user.UserUpdateRequest;
import com.rentinhand.rihtracker.entities.User;

public interface UserService {
    public User createUser(UserCreateRequest userData);
    public User updateUser(User user, UserUpdateRequest userData);
    public boolean deleteUser(User user);
}
