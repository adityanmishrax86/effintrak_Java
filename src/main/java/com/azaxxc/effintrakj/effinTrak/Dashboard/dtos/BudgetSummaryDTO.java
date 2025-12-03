package com.azaxxc.effintrakj.effinTrak.Dashboard.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetSummaryDTO {
    private Long budgetId;
    private String categoryName;
    private Double budgetAmount;
    private Double spentAmount;
    private Double remainingAmount;
    private Double percentageUsed;
}

