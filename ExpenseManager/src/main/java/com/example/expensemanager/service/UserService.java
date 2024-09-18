package com.example.expensemanager.service;

import com.example.expensemanager.dto.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final RestTemplate restTemplate;

    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void  createUser(CreateUserRequest request) {
        // Generate UUID for the expense
//        UUID expenseId = UUID.randomUUID();
//
//        // Prepare the request body to send to the database service
//        String databaseServiceUrl = "http://localhost:8090/";// URL of the Database Service Application
//        Expense expense = new Expense();
//        expense.setId(expenseId);
//        expense.setAmount(request.getAmount());
//        expense.setDescription(request.getDescription());
//        expense.setSplitType(request.getSplitType());
//        expense.setDate(LocalDate.now());
//
//        restTemplate.postForObject(databaseServiceUrl + "expenses", expense, Expense.class);
//
//        for (String friendId : request.getFriends()) {
//            CreateExpenseAllocationRequest allocationRequest = new CreateExpenseAllocationRequest();
//            allocationRequest.setExpenseId(expenseId);
//            allocationRequest.setFriendId(friendId);
//
//            restTemplate.postForObject(databaseServiceUrl + "expenseAllocation", allocationRequest, CreateExpenseAllocationRequest.class);
//        }


    }
}
