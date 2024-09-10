package org.example.billsSplitApp.service;


import org.example.billsSplitApp.model.*;
import org.example.billsSplitApp.repository.ExpensesAllocationsRepository;
import org.example.billsSplitApp.repository.ExpensesRepository;
import org.example.billsSplitApp.repository.FriendsRepository;
import org.example.billsSplitApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
