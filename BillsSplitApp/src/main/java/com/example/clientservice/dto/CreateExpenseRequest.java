package com.example.clientservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class CreateExpenseRequest {

    private BigDecimal amount;
    private String description;
    private String splitType; // "EXACT_AMOUNT" or "SPLIT_EQUALLY"
    private LocalDate date;
    private List<User> friends;
    private String initatorUser;// List of friend IDs
}
