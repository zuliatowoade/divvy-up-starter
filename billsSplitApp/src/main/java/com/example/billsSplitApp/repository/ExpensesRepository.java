package com.example.billsSplitApp.repository;

import com.example.billsSplitApp.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expense, Long> {
}
