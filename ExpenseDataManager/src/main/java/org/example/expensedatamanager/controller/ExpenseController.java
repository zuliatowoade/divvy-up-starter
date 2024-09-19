package org.example.expensedatamanager.controller;

import org.example.expensedatamanager.model.Expense;
import org.example.expensedatamanager.service.ExpenseService;
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
    public ResponseEntity<List<Expense>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.saveExpense(expense));
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        var existingExpenseOpt = expenseService.getExpenseById(id);
        if (existingExpenseOpt != null) {
            var newExpense = new Expense();

            // Update the existing expense's fields with the new values
            newExpense.setAmount(existingExpenseOpt.getAmount());
            newExpense.setDescription(existingExpenseOpt.getDescription());
            newExpense.setSplitType(existingExpenseOpt.getSplitType());
            newExpense.setDate(existingExpenseOpt.getDate());

            var updatedExpense = expenseService.saveExpense(newExpense);
            return ResponseEntity.ok(updatedExpense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

