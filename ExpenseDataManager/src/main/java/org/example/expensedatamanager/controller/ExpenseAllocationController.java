package org.example.expensedatamanager.controller;

import org.example.expensedatamanager.model.CreateExpenseAllocationRequest;
import org.example.expensedatamanager.model.ExpensesAllocations;
import org.example.expensedatamanager.model.Friends;
import org.example.expensedatamanager.service.ExpenseAllocationService;
import org.example.expensedatamanager.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
        if (request.getFriendId() != null) {
            ExpensesAllocations allocation = new ExpensesAllocations();
            allocation.setExpenseId(request.getExpenseId().toString());
            allocation.setInitiatorId(request.getInitatorUser());
            allocation.setAmount(request.getAmount().longValue());
            allocation.setFriend(request.getFriendId());

            ExpensesAllocations savedAllocation = expenseAllocationService.saveExpense(allocation);
            return ResponseEntity.ok(savedAllocation);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
