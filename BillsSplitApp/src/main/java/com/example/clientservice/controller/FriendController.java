package com.example.clientservice.controller;

import com.example.clientservice.dto.CreateUserRequest;
import com.example.clientservice.dto.Friend;
import com.example.clientservice.service.FriendService;
import com.example.clientservice.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FriendController {

    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping("/getAllFriends/{initiatorId}")
    public List<User> createFriend(@PathVariable String initiatorId) {
        return friendService.findFriendsByInitiatorId(initiatorId);
    }
}