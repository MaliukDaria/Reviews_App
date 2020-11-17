package com.example.demo.controller;

import com.example.demo.model.dto.UserResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private
    @GetMapping("/by-profile-name")
    public UserResponseDto getUser(@RequestParam String profileName) {

    }
}
