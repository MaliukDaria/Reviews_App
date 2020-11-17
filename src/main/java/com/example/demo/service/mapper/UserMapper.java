package com.example.demo.service.mapper;

import com.example.demo.model.ReviewDto;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToUser(ReviewDto reviewDto) {
        User user = new User();
        user.setExternalId(reviewDto.getUserId());
        user.setProfileName(reviewDto.getProfileName());
        return user;
    }

    public UserResponseDto mapToUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setProfileName(user.getProfileName());
        return userResponseDto;
    }
}
