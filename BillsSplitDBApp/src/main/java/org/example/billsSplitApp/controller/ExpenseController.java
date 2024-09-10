package org.example.billsSplitApp.controller;

import org.example.billsSplitApp.model.Expense;
import org.example.billsSplitApp.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        Expense existingExpenseOpt = expenseService.getExpenseById(id);

        if (existingExpenseOpt != null) {
            Expense newExpense = existingExpenseOpt;

            // Update the existing expense's fields with the new values
            newExpense.setAmount(existingExpenseOpt.getAmount());
            newExpense.setDescription(existingExpenseOpt.getDescription());
            newExpense.setSplitType(existingExpenseOpt.getSplitType());
            newExpense.setDate(existingExpenseOpt.getDate());

            Expense updatedExpense = expenseService.saveExpense(newExpense);
            return ResponseEntity.ok(updatedExpense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

