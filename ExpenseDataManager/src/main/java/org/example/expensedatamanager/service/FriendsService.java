package org.example.expensedatamanager.service;


import org.example.expensedatamanager.model.CreateFriendsRequest;
import org.example.expensedatamanager.model.Friends;
import org.example.expensedatamanager.model.User;
import org.example.expensedatamanager.repository.FriendsRepository;
import org.example.expensedatamanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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


    public List<Friends> getAllFriends() {
        return friendsRepository.findAll();
    }

    public Friends getFriendsById(Long id) {
        return friendsRepository.findById(id).orElse(null);
    }

    public List<User> getFriendsByInitiatorName(String username) {
        var result = friendsRepository.findByInitiatorName(username);
        List<User> response = new ArrayList<User>();
        for (Friends friend : result) {
            response.add(friend.getFriend());
        }
        return response;
    }

    public Friends saveUser(Friends friend) {
        return friendsRepository.save(friend);
    }

    public void deleteFriend(Long id) {
        friendsRepository.deleteById(id);
    }

    public Friends createFriendship(CreateFriendsRequest request) {
        Optional<User> userOpt = userRepository.findById(request.getUserId());

        if (userOpt.isPresent()) {
            var user = userOpt.get();
            var friend = generateFriend(user);

            Friends friendship = new Friends();
           // friendship.setUserId(user.getId());
            //friendship.setDate(request.getDate());

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
