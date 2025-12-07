package com.azaxxc.effintrakj.effinTrak.Expense.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExpenseResponse {
    private String description;
    private double amount;
    private String category;
    private String date;
    private String paymentMethod;
    private String bankAccount;
    private String paidTo;
    private boolean isRecurring;
    private Long id;
}
