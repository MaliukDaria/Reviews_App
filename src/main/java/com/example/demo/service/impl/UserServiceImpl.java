package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.model.dto.UserResponseDto;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public void addAll(List<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public List<UserResponseDto> getActiveUsers(int numberOfUsers) {
        return userRepository.getActiveUsers(PageRequest.of(0, numberOfUsers));
    }
}
