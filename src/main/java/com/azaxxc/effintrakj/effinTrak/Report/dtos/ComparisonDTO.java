package com.azaxxc.effintrakj.effinTrak.Report.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComparisonDTO {
    private Double totalIncome;
    private Double totalExpense;
    private Double balance;
    private Double incomePercentage;
    private Double expensePercentage;
    private Map<String, Double> topIncomeCategories;
    private Map<String, Double> topExpenseCategories;
}

