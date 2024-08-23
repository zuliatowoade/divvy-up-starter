package org.example.billsSplitApp.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesAllocations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "expense_id", nullable = false)
    private String expenseId;

//    @ManyToOne
//    @JoinColumn(name = "friend_id", nullable = false)
    private Long friend;
    private Long initiatorId;
}


