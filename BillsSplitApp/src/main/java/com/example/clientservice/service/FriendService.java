package com.example.clientservice.service;

import com.example.clientservice.dto.CreateExpenseAllocationRequest;
import com.example.clientservice.dto.CreateExpenseRequest;
import com.example.clientservice.dto.Expense;
import com.example.clientservice.dto.Friend;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class FriendService {

    private final RestTemplate restTemplate;

    @Autowired
    public FriendService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> findFriendsByInitiatorId(String initiator) {
        // Prepare the request body to send to the database service
        String databaseServiceUrl = "http://localhost:8090/friends/";// URL of the Database Service Application

        return restTemplate.getForObject(databaseServiceUrl + initiator, List.class);
    }
}
