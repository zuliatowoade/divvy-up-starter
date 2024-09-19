package com.example.expensemanager.controller;

import com.example.expensemanager.dto.CreateUserRequest;
import com.example.expensemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request) {
        userService.createUser(request);
        return ResponseEntity.ok("User created successfully");
    }
}
