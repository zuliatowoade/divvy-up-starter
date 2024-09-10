package org.example.billsSplitApp.repository;

import org.example.billsSplitApp.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expense, Long> {
}
