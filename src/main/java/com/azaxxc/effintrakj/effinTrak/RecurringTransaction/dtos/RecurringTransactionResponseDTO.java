package com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecurringTransactionResponseDTO {
    private Long id;
    private String description;
    private Double amount;
    private String type;
    private String categoryName;
    private String bankAccountName;
    private String frequency;
    private String startDate;
    private String endDate;
    private String nextDueDate;
    private String paymentMethod;
    private String paidTo;
    private String source;
    private String note;
    private Boolean isActive;
}

