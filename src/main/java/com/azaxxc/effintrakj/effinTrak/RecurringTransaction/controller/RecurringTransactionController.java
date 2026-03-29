package com.azaxxc.effintrakj.effinTrak.RecurringTransaction.controller;

import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos.RecurringTransactionRequestDTO;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos.RecurringTransactionResponseDTO;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos.UpdateRecurringTransactionRequestDTO;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.service.RecurringTransactionService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.security.AuthenticatedUserResolver;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recurring-transactions")
public class RecurringTransactionController {

    private final RecurringTransactionService recurringTransactionService;
    private final UserService userService;
    private final GlobalResponseService globalResponseService;
    private final AuthenticatedUserResolver authenticatedUserResolver;

    @Autowired
    public RecurringTransactionController(RecurringTransactionService recurringTransactionService,
                                          UserService userService,
                                          GlobalResponseService globalResponseService,
                                          AuthenticatedUserResolver authenticatedUserResolver) {
        this.recurringTransactionService = recurringTransactionService;
        this.userService = userService;
        this.globalResponseService = globalResponseService;
        this.authenticatedUserResolver = authenticatedUserResolver;
    }

    @PostMapping
    public ResponseEntity<Object> createRecurringTransaction(@Valid @RequestBody RecurringTransactionRequestDTO dto,
                                                             Authentication authentication) {
        Long userId = authenticatedUserResolver.resolveRequestedUserId(authentication, dto.getUserId());
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        RecurringTransactionResponseDTO saved = recurringTransactionService.saveRecurringTransaction(dto, user);
        return globalResponseService.success(saved, "Recurring transaction created successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getRecurringTransactionsByUserId(@PathVariable Long userId, Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        List<RecurringTransactionResponseDTO> transactions = recurringTransactionService.getRecurringTransactionsByUserId(effectiveUserId);
        return globalResponseService.success(transactions, "Fetched recurring transactions for user");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRecurringTransactionById(@PathVariable Long id) {
        // This would need a method in service to get by ID
        return globalResponseService.error("Not implemented", org.springframework.http.HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRecurringTransaction(@PathVariable Long id,
                                                             @RequestBody UpdateRecurringTransactionRequestDTO dto,
                                                             Authentication authentication) {
        Long authenticatedUserId = authenticatedUserResolver.resolveUserId(authentication);
        RecurringTransactionResponseDTO updated = authenticatedUserId == null
                ? recurringTransactionService.updateRecurringTransaction(id, dto)
                : recurringTransactionService.updateRecurringTransactionForUser(authenticatedUserId, id, dto);
        return globalResponseService.success(updated, "Recurring transaction updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRecurringTransaction(@PathVariable Long id, Authentication authentication) {
        Long authenticatedUserId = authenticatedUserResolver.resolveUserId(authentication);
        if (authenticatedUserId == null) {
            recurringTransactionService.deleteRecurringTransaction(id);
        } else {
            recurringTransactionService.deleteRecurringTransactionForUser(authenticatedUserId, id);
        }
        return globalResponseService.success("Recurring transaction deleted successfully");
    }
}
