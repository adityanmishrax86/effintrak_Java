package com.azaxxc.effintrakj.effinTrak.Expense.controller;

import com.azaxxc.effintrakj.effinTrak.Expense.dtos.ExpenseResponse;
import com.azaxxc.effintrakj.effinTrak.Expense.dtos.NewExpenseRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Expense.dtos.UpdateExpenseRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Expense.model.Expense;
import com.azaxxc.effintrakj.effinTrak.Expense.service.ExpenseService;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.IncomeResponse;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.UpdateIncomeRequestDTO;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.dtos.PageableResponse;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final UserService userService;
    private final GlobalResponseService globalResponseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService, UserService userService, GlobalResponseService globalResponseService) {
        this.expenseService = expenseService;
        this.userService = userService;
        this.globalResponseService = globalResponseService;
    }
    @PostMapping
    public ResponseEntity<Object> createExpense(@Valid @RequestBody NewExpenseRequestDTO dto) {
        Long userId = dto.getUserId();
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        expenseService.saveExpense(dto, user);
        return globalResponseService.success("Expense added successfully");

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getExpenseByUserId(
            @PathVariable Long userId,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount,
            @RequestParam(required = false) String paymentMethod,
            @RequestParam(required = false) Long bankAccountId,
            Pageable pageable) {
        Page<ExpenseResponse> expenses;
        
        // Use advanced filtering if any filter parameters are provided
        if (categoryId != null || minAmount != null || maxAmount != null || 
            paymentMethod != null || bankAccountId != null || (start != null && end != null)) {
            expenses = expenseService.getExpensesWithFilters(
                    userId, categoryId, minAmount, maxAmount, paymentMethod, bankAccountId, start, end, pageable);
        } else if (null != start && null != end) {
            expenses = expenseService.getExpenseByUserIdBetweenDatePeriods(userId, start, end, pageable);
        } else {
            expenses = expenseService.getExpenseByUserId(userId, pageable);
        }
        PageableResponse<ExpenseResponse> response  = new PageableResponse<>(expenses.getContent(), expenses);

        return globalResponseService.success(response, "Fetched expenses for user");
    }

    @GetMapping("/user/{userId}/search")
    public ResponseEntity<Object> searchExpenses(
            @PathVariable Long userId,
            @RequestParam String search) {
        List<ExpenseResponse> expenses = expenseService.searchExpensesByDescription(userId, search);
        return globalResponseService.success(expenses, "Search results for expenses");
    }

    @PutMapping("/user/{expenseId}")
    public ResponseEntity<Object> updateExpense(@PathVariable Long expenseId, @RequestBody UpdateExpenseRequestDTO dto) {
        ExpenseResponse icr = expenseService.updateExpenseDetail(expenseId, dto);

        return globalResponseService.success(icr, "Income updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}

