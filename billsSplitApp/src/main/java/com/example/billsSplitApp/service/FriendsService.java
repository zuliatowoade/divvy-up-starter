package com.example.billsSplitApp.service;


import com.example.billsSplitApp.model.CreateFriendsRequest;
import com.example.billsSplitApp.model.Friends;
import com.example.billsSplitApp.model.User;
import com.example.billsSplitApp.repository.FriendsRepository;
import com.example.billsSplitApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendsService {

    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;

    @Autowired
    public FriendsService(FriendsRepository friendsRepository, UserRepository userRepository) {
        this.friendsRepository = friendsRepository;
        this.userRepository = userRepository;
    }

    public Friends createFriendship(CreateFriendsRequest request) {
        Optional<User> userOpt = userRepository.findById(request.getUserId());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            User friend = generateFriend(user);

            Friends friendship = new Friends();
            friendship.setUser(user);
            friendship.setFriend(friend);
            friendship.setDate(request.getDate());

            return friendsRepository.save(friendship);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    private User generateFriend(User user) {
        User newFriend = new User();
        newFriend.setFirstName("Friend of " + user.getFirstName());
        newFriend.setLastName(user.getLastName());
        newFriend.setEmail(user.getEmail().replace("@", "+friend@"));
        newFriend.setPhoneNumber(user.getPhoneNumber());
        //newFriend.setProfilePicture(user.getProfilePicture());

        return userRepository.save(newFriend);
    }
}
