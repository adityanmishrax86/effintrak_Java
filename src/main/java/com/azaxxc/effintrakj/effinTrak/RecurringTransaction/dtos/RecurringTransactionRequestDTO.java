package com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecurringTransactionRequestDTO {
    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Amount is required")
    private Double amount;

    @NotBlank(message = "Type is required")
    private String type; // "INCOME" or "EXPENSE"

    private Long categoryId;

    private Long bankAccountId;

    @NotBlank(message = "Frequency is required")
    private String frequency; // "DAILY", "WEEKLY", "MONTHLY", "YEARLY"

    @NotBlank(message = "Start date is required")
    private String startDate;

    private String endDate;

    private String paymentMethod;

    private String paidTo;
    private String source;
    private String note;

    @NotNull(message = "User ID is required")
    private Long userId;
}

