package com.example.clientservice.controller;

import com.example.clientservice.dto.CreateExpenseRequest;
import com.example.clientservice.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/addExpense")
    public ResponseEntity<String> createExpense(@RequestBody CreateExpenseRequest request) {
        expenseService.createExpense(request);
        return ResponseEntity.ok("Expense created successfully");
    }
}
