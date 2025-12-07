package com.azaxxc.effintrakj.effinTrak.Transaction.controller;

import com.azaxxc.effintrakj.effinTrak.Transaction.dtos.TransactionResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Transaction.service.TransactionService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final GlobalResponseService globalResponseService;

    public TransactionController(TransactionService transactionService, GlobalResponseService globalResponseService) {
        this.transactionService = transactionService;
        this.globalResponseService = globalResponseService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getAllTransactions(@PathVariable Long userId) {
        List<TransactionResponseDTO> transactions = transactionService.getAllTransactions(userId);
        return globalResponseService.success(transactions, "Fetched all transactions for user");
    }

    @GetMapping("/user/{userId}/filter")
    public ResponseEntity<Object> getTransactionsBetweenDates(
            @PathVariable Long userId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        List<TransactionResponseDTO> transactions = transactionService.getTransactionsBetweenDates(userId, startDate,
                endDate);
        return globalResponseService.success(transactions, "Fetched transactions for user between dates");
    }

    @GetMapping("/user/{userId}/search")
    public ResponseEntity<Object> searchTransactions(
            @PathVariable Long userId,
            @RequestParam String search) {
        List<TransactionResponseDTO> transactions = transactionService.searchTransactions(userId, search);
        return globalResponseService.success(transactions, "Search results for transactions");
    }
}
