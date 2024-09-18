package org.example.expensedatamanager.model;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateExpenseAllocationRequest {
    private UUID expenseId;
    private String friendId;
    private String initatorUser;
}
