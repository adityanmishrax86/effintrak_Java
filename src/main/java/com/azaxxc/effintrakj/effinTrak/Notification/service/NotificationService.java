package com.azaxxc.effintrakj.effinTrak.Notification.service;

import com.azaxxc.effintrakj.effinTrak.Budget.model.Budget;
import com.azaxxc.effintrakj.effinTrak.Budget.repo.BudgetRepository;
import com.azaxxc.effintrakj.effinTrak.Expense.model.Expense;
import com.azaxxc.effintrakj.effinTrak.Expense.repo.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationService {

    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;

    public NotificationService(BudgetRepository budgetRepository, ExpenseRepository expenseRepository) {
        this.budgetRepository = budgetRepository;
        this.expenseRepository = expenseRepository;
    }

    public void checkBudgetExceeded(Long userId) {
        List<Budget> budgets = budgetRepository.findByUserId(userId);
        LocalDate now = LocalDate.now();

        for (Budget budget : budgets) {
            if (now.isAfter(budget.getStartDate()) && now.isBefore(budget.getEndDate())) {
                Double totalExpense = 0.0;
                if (budget.getCategory() != null) {
                    // Category specific budget
                    List<Expense> expenses = expenseRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId,
                            budget.getStartDate(), budget.getEndDate());
                    totalExpense = expenses.stream()
                            .filter(e -> e.getCategory().getId().equals(budget.getCategory().getId()))
                            .mapToDouble(Expense::getAmount).sum();
                } else {
                    // Overall budget
                    List<Expense> expenses = expenseRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId,
                            budget.getStartDate(), budget.getEndDate());
                    totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();
                }

                if (totalExpense > budget.getAmount()) {
                    sendNotification(userId, "Budget Exceeded for "
                            + (budget.getCategory() != null ? budget.getCategory().getName() : "Overall"));
                } else if (totalExpense > (budget.getAmount() * (budget.getAlertThreshold() / 100))) {
                    sendNotification(userId,
                            "Budget Alert: You have reached " + budget.getAlertThreshold() + "% of your "
                                    + (budget.getCategory() != null ? budget.getCategory().getName() : "Overall")
                                    + " budget.");
                }
            }
        }
    }

    private void sendNotification(Long userId, String message) {
        // Placeholder for actual notification logic (Email/SMS)
        System.out.println("NOTIFICATION for User " + userId + ": " + message);
    }
}
