package com.azaxxc.effintrakj.effinTrak.Dashboard.dtos;

import com.azaxxc.effintrakj.effinTrak.Transaction.dtos.TransactionResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponseDTO {
    private Double totalBalance;
    private Double monthlyIncome;
    private Double monthlyExpense;
    private Double monthlyBalance;
    private List<TransactionResponseDTO> recentTransactions;
    private List<BudgetSummaryDTO> budgetSummary;
    private List<UpcomingBillDTO> upcomingBills;
    private List<SavingsProgressDTO> savingsProgress;
}

