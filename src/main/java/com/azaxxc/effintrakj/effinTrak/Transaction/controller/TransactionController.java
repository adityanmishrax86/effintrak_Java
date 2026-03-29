package com.azaxxc.effintrakj.effinTrak.Transaction.controller;

import com.azaxxc.effintrakj.effinTrak.Transaction.dtos.TransactionResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Transaction.service.TransactionService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.security.AuthenticatedUserResolver;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final GlobalResponseService globalResponseService;
    private final AuthenticatedUserResolver authenticatedUserResolver;

    public TransactionController(TransactionService transactionService,
                                 GlobalResponseService globalResponseService,
                                 AuthenticatedUserResolver authenticatedUserResolver) {
        this.transactionService = transactionService;
        this.globalResponseService = globalResponseService;
        this.authenticatedUserResolver = authenticatedUserResolver;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getAllTransactions(@PathVariable Long userId, Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        List<TransactionResponseDTO> transactions = transactionService.getAllTransactions(effectiveUserId);
        return globalResponseService.success(transactions, "Fetched all transactions for user");
    }

    @GetMapping("/user/{userId}/filter")
    public ResponseEntity<Object> getTransactionsBetweenDates(
            @PathVariable Long userId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        List<TransactionResponseDTO> transactions = transactionService.getTransactionsBetweenDates(effectiveUserId, startDate,
                endDate);
        return globalResponseService.success(transactions, "Fetched transactions for user between dates");
    }

    @GetMapping("/user/{userId}/search")
    public ResponseEntity<Object> searchTransactions(
            @PathVariable Long userId,
            @RequestParam String search,
            Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        List<TransactionResponseDTO> transactions = transactionService.searchTransactions(effectiveUserId, search);
        return globalResponseService.success(transactions, "Search results for transactions");
    }
}
