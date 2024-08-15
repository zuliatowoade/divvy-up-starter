package com.example.billsSplitApp.repository;

import com.example.billsSplitApp.model.ExpensesAllocations;
import com.example.billsSplitApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesAllocationsRepository extends JpaRepository<ExpensesAllocations, Long> {
}
