package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.dto.UserResponseDto;
import java.util.List;

public interface UserService extends GenericService<User> {
    List<UserResponseDto> getActiveUsers(int numberOfUsers);
}
