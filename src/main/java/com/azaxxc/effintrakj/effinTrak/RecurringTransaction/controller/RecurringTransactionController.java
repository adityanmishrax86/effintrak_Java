package com.azaxxc.effintrakj.effinTrak.RecurringTransaction.controller;

import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos.RecurringTransactionRequestDTO;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos.RecurringTransactionResponseDTO;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos.UpdateRecurringTransactionRequestDTO;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.service.RecurringTransactionService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recurring-transactions")
public class RecurringTransactionController {

    private final RecurringTransactionService recurringTransactionService;
    private final UserService userService;
    private final GlobalResponseService globalResponseService;

    @Autowired
    public RecurringTransactionController(RecurringTransactionService recurringTransactionService,
                                          UserService userService,
                                          GlobalResponseService globalResponseService) {
        this.recurringTransactionService = recurringTransactionService;
        this.userService = userService;
        this.globalResponseService = globalResponseService;
    }

    @PostMapping
    public ResponseEntity<Object> createRecurringTransaction(@Valid @RequestBody RecurringTransactionRequestDTO dto) {
        Long userId = dto.getUserId();
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        RecurringTransactionResponseDTO saved = recurringTransactionService.saveRecurringTransaction(dto, user);
        return globalResponseService.success(saved, "Recurring transaction created successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getRecurringTransactionsByUserId(@PathVariable Long userId) {
        List<RecurringTransactionResponseDTO> transactions = recurringTransactionService.getRecurringTransactionsByUserId(userId);
        return globalResponseService.success(transactions, "Fetched recurring transactions for user");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRecurringTransactionById(@PathVariable Long id) {
        // This would need a method in service to get by ID
        return globalResponseService.error("Not implemented", org.springframework.http.HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRecurringTransaction(@PathVariable Long id, @RequestBody UpdateRecurringTransactionRequestDTO dto) {
        RecurringTransactionResponseDTO updated = recurringTransactionService.updateRecurringTransaction(id, dto);
        return globalResponseService.success(updated, "Recurring transaction updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRecurringTransaction(@PathVariable Long id) {
        recurringTransactionService.deleteRecurringTransaction(id);
        return globalResponseService.success("Recurring transaction deleted successfully");
    }
}

