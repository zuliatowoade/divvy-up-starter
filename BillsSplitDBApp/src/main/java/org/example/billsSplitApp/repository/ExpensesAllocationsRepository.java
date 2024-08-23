package org.example.billsSplitApp.repository;

import org.example.billsSplitApp.model.ExpensesAllocations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesAllocationsRepository extends JpaRepository<ExpensesAllocations, Long> {
}
