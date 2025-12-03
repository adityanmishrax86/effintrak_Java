package com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRecurringTransactionRequestDTO {
    private String description;
    private Double amount;
    private String type;
    private Long categoryId;
    private Long bankAccountId;
    private String frequency;
    private String startDate;
    private String endDate;
    private String paymentMethod;
    private String paidTo;
    private String source;
    private String note;
    private Boolean isActive;
}

