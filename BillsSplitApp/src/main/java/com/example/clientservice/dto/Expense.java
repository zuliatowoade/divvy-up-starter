package com.example.clientservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    private UUID id;
    private BigDecimal amount;
    private String description;
    private String splitType;

    private LocalDate date;

    public enum SplitType {
        EXACT_AMOUNT,
        SPLIT_EQUALLY
    }
}
