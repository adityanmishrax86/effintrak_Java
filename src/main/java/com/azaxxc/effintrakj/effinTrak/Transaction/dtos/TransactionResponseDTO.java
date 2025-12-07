package com.azaxxc.effintrakj.effinTrak.Transaction.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {
    private Long id;
    private String description;
    private Double amount;
    private String date;
    private String type; // "INCOME" or "EXPENSE"
    private String categoryName;
    private String sourceOrPaidTo; // Source for Income, PaidTo for Expense
}
