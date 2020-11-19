package com.example.demo.controller;

import com.example.demo.model.dto.UserResponseDto;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final String NUMBER_OF_USERS = "1000";
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/most-active")
    @ApiOperation(value = "Get most active users")
    public List<UserResponseDto> getUser(
            @ApiParam(value = "You can enter number of requested active users")
            @RequestParam(defaultValue = NUMBER_OF_USERS) int numberOfUsers) {
        return userService.getActiveUsers(numberOfUsers);
    }
}

