package org.example.expensedatamanager.repository;

import org.example.expensedatamanager.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expense, Long> {
}
