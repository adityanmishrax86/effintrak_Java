package com.azaxxc.effintrakj.effinTrak.Expense.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateExpenseRequestDTO {
    private String description;
    private Double amount;
    private String date; // Consider using LocalDate for better date handling
    private Long categoryId;
    private String paymentMethod;
    private String paidTo;
    private boolean isRecurring;
    private Long userId;
}
