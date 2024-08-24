package com.example.clientservice.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateUserRequest {

    private double amount;
    private String description;
    private String splitType; // "EXACT_AMOUNT" or "SPLIT_EQUALLY"
    private LocalDate date;
    private List<String> friends;
    private String initator;// List of friend IDs
}
