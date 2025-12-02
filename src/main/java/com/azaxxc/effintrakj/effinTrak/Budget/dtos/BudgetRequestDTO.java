package com.azaxxc.effintrakj.effinTrak.Budget.dtos;

import lombok.Data;

@Data
public class BudgetRequestDTO {
    private Double amount;
    private String startDate;
    private String endDate;
    private Long categoryId; // Optional
    private Long userId;
    private Double alertThreshold;
}
