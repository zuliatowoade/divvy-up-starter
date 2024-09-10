package org.example.billsSplitApp.model;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateExpenseAllocationRequest {
    private UUID expenseId;
    private Long friendId;
}
