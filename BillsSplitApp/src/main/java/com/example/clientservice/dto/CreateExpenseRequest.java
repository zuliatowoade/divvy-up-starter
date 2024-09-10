package com.example.clientservice.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class CreateExpenseRequest {

    private double amount;
    private String description;
    private String splitType; // "EXACT_AMOUNT" or "SPLIT_EQUALLY"
    private LocalDate date;
    private List<String> friends;
    private String initatorUser;// List of friend IDs
}
