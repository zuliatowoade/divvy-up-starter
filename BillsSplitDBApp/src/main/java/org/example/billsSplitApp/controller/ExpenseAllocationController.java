package org.example.billsSplitApp.controller;

import org.example.billsSplitApp.model.CreateExpenseAllocationRequest;
import org.example.billsSplitApp.model.Expense;
import org.example.billsSplitApp.model.ExpensesAllocations;
import org.example.billsSplitApp.model.Friends;
import org.example.billsSplitApp.repository.FriendsRepository;
import org.example.billsSplitApp.service.ExpenseAllocationService;
import org.example.billsSplitApp.service.ExpenseService;
import org.example.billsSplitApp.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/expenseAllocation")
public class ExpenseAllocationController {

    private final ExpenseAllocationService expenseAllocationService;
    private final FriendsService friendsService;

    @Autowired
    public ExpenseAllocationController(ExpenseAllocationService expenseAllocationService, FriendsService friendsService) {
        this.expenseAllocationService = expenseAllocationService;
        this.friendsService = friendsService;
    }

    @PostMapping
    public ResponseEntity<ExpensesAllocations> createExpenseAllocation(@RequestBody CreateExpenseAllocationRequest request) {
        Friends friendOpt = friendsService.getFriendsById(request.getFriendId());

        if (friendOpt != null) {
            ExpensesAllocations allocation = new ExpensesAllocations();
            allocation.setExpenseId(request.getExpenseId().toString());
            allocation.setFriend(friendOpt.getId());

            ExpensesAllocations savedAllocation = expenseAllocationService.saveExpense(allocation);
            return ResponseEntity.ok(savedAllocation);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
