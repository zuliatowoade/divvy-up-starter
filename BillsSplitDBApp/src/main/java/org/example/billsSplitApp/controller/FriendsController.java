package org.example.billsSplitApp.controller;


import org.example.billsSplitApp.model.CreateFriendsRequest;
import org.example.billsSplitApp.model.FriendResponse;
import org.example.billsSplitApp.model.Friends;
import org.example.billsSplitApp.model.User;
import org.example.billsSplitApp.repository.FriendsRepository;
import org.example.billsSplitApp.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
    public class FriendsController {

        private final FriendsService friendsService;

        @Autowired
        public FriendsController(FriendsService friendsService) {
            this.friendsService = friendsService;
        }

        @PostMapping("/create")
        public ResponseEntity<org.example.billsSplitApp.model.Friends> createFriendship(@RequestBody CreateFriendsRequest request) {
            try {
                org.example.billsSplitApp.model.Friends friendship = friendsService.createFriendship(request);
                return ResponseEntity.ok(friendship);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(null);
            }
        }

    @GetMapping("/{username}")
    public List<User> getAllFriends(@PathVariable String username) {
        return friendsService.getFriendsByInitiatorName(username);
    }
    }
