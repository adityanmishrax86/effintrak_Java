package com.azaxxc.effintrakj.effinTrak.Report.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDTO {
    private Double totalIncome;
    private Double totalExpense;
    private Double balance;
    private Map<String, Double> incomeByCategory;
    private Map<String, Double> expenseByCategory;
}
