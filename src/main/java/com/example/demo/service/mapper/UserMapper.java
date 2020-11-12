package com.example.demo.service.mapper;

import com.example.demo.model.ReviewDto;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User mapToUser(ReviewDto reviewDto) {
        User user = new User();
        user.setExternalId(reviewDto.getUserId());
        user.setHelpfulnessDenominator(reviewDto.getHelpfulnessDenominator());
        user.setHelpfulnessNumerator(reviewDto.getHelpfulnessDenominator());
        user.setProfileName(reviewDto.getProfileName());
        return user;
    }
}
