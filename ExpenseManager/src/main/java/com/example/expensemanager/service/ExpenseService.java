package com.example.expensemanager.service;

import com.example.expensemanager.dto.CreateExpenseRequest;
import com.example.expensemanager.dto.CreateExpenseAllocationRequest;
import com.example.expensemanager.dto.Expense;
import com.example.expensemanager.kafka.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ExpenseService {

    private final RestTemplate restTemplate;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public ExpenseService(RestTemplate restTemplate, KafkaProducerService kafkaProducerService) {
        this.restTemplate = restTemplate;
        this.kafkaProducerService = kafkaProducerService;
    }

    public void  createExpense(CreateExpenseRequest request) {
        // Generate UUID for the expense
        var expenseId = UUID.randomUUID();

        // Prepare the request body to send to the database service
        var databaseServiceUrl = "http://localhost:8090/";// URL of the Database Service Application
        var expense = new Expense();
        expense.setId(expenseId);
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setSplitType(request.getSplitType());
        expense.setDate(LocalDate.now());

        var updatedExpenses = restTemplate.postForObject(databaseServiceUrl + "expenses", expense, Expense.class);
        if(updatedExpenses != null) {
            var message = "Expense added: " + updatedExpenses.getId() + " - " + updatedExpenses.getDescription();
            // Publish the event to Kafka
            kafkaProducerService.sendMessage("expenses-topic", message);
        }
        for (var friend : request.getFriends()) {
            CreateExpenseAllocationRequest allocationRequest = new CreateExpenseAllocationRequest();
            allocationRequest.setExpenseId(expenseId);
            allocationRequest.setFriendId(friend.getUsername());
            allocationRequest.setInitatorUser(request.getInitatorUser());
            allocationRequest.setAmount(friend.getAmount());

            restTemplate.postForObject(databaseServiceUrl + "expenseAllocation", allocationRequest, CreateExpenseAllocationRequest.class);
        }
    }
}
