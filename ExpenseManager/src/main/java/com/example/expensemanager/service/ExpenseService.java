package com.example.expensemanager.service;

import com.example.expensemanager.dto.CreateExpenseRequest;
import com.example.expensemanager.kafka.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        throw new UnsupportedOperationException("This method is not yet implemented");

    }
}
