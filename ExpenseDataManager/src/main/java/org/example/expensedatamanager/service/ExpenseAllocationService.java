package org.example.expensedatamanager.service;


import org.example.expensedatamanager.model.*;
import org.example.expensedatamanager.repository.ExpensesAllocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseAllocationService {
    private final ExpensesAllocationsRepository expensesAllocationsRepository;

    @Autowired
    public ExpenseAllocationService(ExpensesAllocationsRepository expensesAllocationsRepository) {
        this.expensesAllocationsRepository = expensesAllocationsRepository;
    }

//    public List<Expense> getAllExpenses() {
//        return expenseRepository.findAll();
//    }

    public ExpensesAllocations getExpenseById(Long id) {
        return expensesAllocationsRepository.findById(id).orElse(null);
    }

    public ExpensesAllocations saveExpense(ExpensesAllocations e) {
        return expensesAllocationsRepository.save(e);
    }

    public void deleteExpense(Long id) {
        expensesAllocationsRepository.deleteById(id);
    }
}
