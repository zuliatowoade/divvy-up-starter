package com.example.expensemanager.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class CreateExpenseRequest {

    private BigDecimal amount;
    private String description;
    private String splitType; // "EXACT_AMOUNT" or "SPLIT_EQUALLY"
    private LocalDate date;
    private List<User> friends;
    private String initatorUser;// List of friend IDs
}
