package org.example.expensedatamanager.repository;

import org.example.expensedatamanager.model.ExpensesAllocations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesAllocationsRepository extends JpaRepository<ExpensesAllocations, Long> {
}
