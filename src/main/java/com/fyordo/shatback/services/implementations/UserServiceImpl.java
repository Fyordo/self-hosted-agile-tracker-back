package com.fyordo.shatback.services.implementations;

import com.fyordo.shatback.dto.requests.user.UserCreateRequest;
import com.fyordo.shatback.dto.requests.user.UserUpdateRequest;
import com.fyordo.shatback.entities.User;
import com.fyordo.shatback.repos.UserRepository;
import com.fyordo.shatback.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User createUser(UserCreateRequest userData) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userData, User.class);
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(User user, UserUpdateRequest userData) {
        user = user.setUsername(userData.getUsername());
        user = user.setLogin(userData.getLogin());
        user = user.setAvatar(userData.getAvatar());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
