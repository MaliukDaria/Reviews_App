package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.dto.UserResponseDto;

public interface UserService extends GenericService<User> {
   UserResponseDto getUserByProfileName(String profileName);
}
