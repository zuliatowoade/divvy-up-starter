package org.example.billsSplitApp.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private double amount;
    private String description;

    @Enumerated(EnumType.STRING)
    private SplitType splitType;

    private LocalDate date;

    public enum SplitType {
        EXACT_AMOUNT,
        SPLIT_EQUALLY
    }
}
