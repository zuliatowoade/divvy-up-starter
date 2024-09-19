package org.example.expensedatamanager.controller;


import org.example.expensedatamanager.model.CreateFriendsRequest;
import org.example.expensedatamanager.model.Friends;
import org.example.expensedatamanager.model.User;
import org.example.expensedatamanager.service.FriendsService;
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
    public ResponseEntity<Friends> createFriendship(@RequestBody CreateFriendsRequest request) {
        try {
            Friends friendship = friendsService.createFriendship(request);
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
