package com.example.expensemanager.service;

import com.example.expensemanager.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FriendService {

    private final RestTemplate restTemplate;

    @Autowired
    public FriendService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> findFriendsByInitiatorId(String initiator) {
        // Prepare the request body to send to the database service
        var databaseServiceUrl = "http://localhost:8090/friends/";// URL of the Database Service Application

        return restTemplate.getForObject(databaseServiceUrl + initiator, List.class);
    }
}
