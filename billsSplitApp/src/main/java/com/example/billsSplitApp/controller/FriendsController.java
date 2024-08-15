package com.example.billsSplitApp.controller;

import com.example.billsSplitApp.model.CreateFriendsRequest;
import com.example.billsSplitApp.model.Friends;
import com.example.billsSplitApp.model.User;
import com.example.billsSplitApp.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/friends")
    public class FriendsController {

        private final FriendsService friendsService;

        @Autowired
        public FriendsController(FriendsService friendsService) {
            this.friendsService = friendsService;
        }

        @PostMapping("/create")
        public ResponseEntity<Friends> createFriendship(CreateFriendsRequest request) {
            try {
                Friends friendship = friendsService.createFriendship(request);
                return ResponseEntity.ok(friendship);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(null);
            }
        }
    }
