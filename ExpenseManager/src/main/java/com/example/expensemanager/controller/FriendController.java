package com.example.expensemanager.controller;

import com.example.expensemanager.dto.User;
import com.example.expensemanager.service.FriendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<User>> createFriend(@PathVariable String initiatorId) {
        return ResponseEntity.ok(friendService.findFriendsByInitiatorId(initiatorId));
    }

    @MessageMapping("/getUsers")
    @SendTo("/topic/users")
    public List<User> getAllUsers() {
        // Fetch all users from the database and send them to the frontend
        return friendService.findFriendsByInitiatorId("cthomson");
    }
}
