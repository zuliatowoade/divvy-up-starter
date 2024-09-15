package org.example.billsSplitApp.service;

import org.example.billsSplitApp.kafka.KafkaProducerService;
import org.example.billsSplitApp.repository.ExpensesRepository;
import org.example.billsSplitApp.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpensesRepository expenseRepository;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public ExpenseService(ExpensesRepository expenseRepository, KafkaProducerService kafkaProducerService) {
        this.expenseRepository = expenseRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public Expense saveExpense(Expense expense) {
        var savedExpense = expenseRepository.save(expense);

        var message = "Expense added: " + savedExpense.getId() + " - " + savedExpense.getDescription();

        // Publish the event to Kafka
        kafkaProducerService.sendMessage("expenses-topic", message);
        return savedExpense;
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}

