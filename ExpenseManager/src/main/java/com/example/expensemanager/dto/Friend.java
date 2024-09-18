package com.example.expensemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
    private Long id;
    private String userId;
    private String friendId;
    private String initiatorName;
}
