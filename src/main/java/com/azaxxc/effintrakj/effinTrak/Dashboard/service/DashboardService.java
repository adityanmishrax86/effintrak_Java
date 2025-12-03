package com.azaxxc.effintrakj.effinTrak.Dashboard.service;

import com.azaxxc.effintrakj.effinTrak.Budget.model.Budget;
import com.azaxxc.effintrakj.effinTrak.Budget.repo.BudgetRepository;
import com.azaxxc.effintrakj.effinTrak.Credits.model.Credit;
import com.azaxxc.effintrakj.effinTrak.Credits.repo.CreditRepository;
import com.azaxxc.effintrakj.effinTrak.Dashboard.dtos.BudgetSummaryDTO;
import com.azaxxc.effintrakj.effinTrak.Dashboard.dtos.DashboardResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Dashboard.dtos.SavingsProgressDTO;
import com.azaxxc.effintrakj.effinTrak.Dashboard.dtos.UpcomingBillDTO;
import com.azaxxc.effintrakj.effinTrak.Expense.model.Expense;
import com.azaxxc.effintrakj.effinTrak.Expense.repo.ExpenseRepository;
import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import com.azaxxc.effintrakj.effinTrak.Income.repo.IncomeRepository;
import com.azaxxc.effintrakj.effinTrak.Savings.model.Savings;
import com.azaxxc.effintrakj.effinTrak.Savings.repo.SavingsRepository;
import com.azaxxc.effintrakj.effinTrak.Subscription.model.Subscription;
import com.azaxxc.effintrakj.effinTrak.Subscription.repo.SubscriptionRepository;
import com.azaxxc.effintrakj.effinTrak.Transaction.dtos.TransactionResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Transaction.service.TransactionService;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.accounts.repo.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final BankAccountRepository bankAccountRepository;
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;
    private final CreditRepository creditRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final SavingsRepository savingsRepository;
    private final TransactionService transactionService;

    public DashboardService(BankAccountRepository bankAccountRepository,
                           IncomeRepository incomeRepository,
                           ExpenseRepository expenseRepository,
                           BudgetRepository budgetRepository,
                           CreditRepository creditRepository,
                           SubscriptionRepository subscriptionRepository,
                           SavingsRepository savingsRepository,
                           TransactionService transactionService) {
        this.bankAccountRepository = bankAccountRepository;
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
        this.budgetRepository = budgetRepository;
        this.creditRepository = creditRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.savingsRepository = savingsRepository;
        this.transactionService = transactionService;
    }

    public DashboardResponseDTO getDashboardData(Long userId) {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);
        LocalDate endOfMonth = now.withDayOfMonth(now.lengthOfMonth());

        // Total balance across all accounts
        List<BankAccount> accounts = bankAccountRepository.findByuser_id(userId);
        Double totalBalance = accounts.stream()
                .mapToDouble(BankAccount::getBalance)
                .sum();

        // Monthly income/expense
        List<Income> monthlyIncomes = incomeRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, startOfMonth, endOfMonth);
        List<Expense> monthlyExpenses = expenseRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, startOfMonth, endOfMonth);

        Double monthlyIncome = monthlyIncomes.stream().mapToDouble(Income::getAmount).sum();
        Double monthlyExpense = monthlyExpenses.stream().mapToDouble(Expense::getAmount).sum();
        Double monthlyBalance = monthlyIncome - monthlyExpense;

        // Recent transactions (last 10)
        List<TransactionResponseDTO> allTransactions = transactionService.getAllTransactions(userId);
        List<TransactionResponseDTO> recentTransactions = allTransactions.stream()
                .limit(10)
                .collect(Collectors.toList());

        // Budget summary
        List<Budget> budgets = budgetRepository.findByUserId(userId);
        List<BudgetSummaryDTO> budgetSummary = budgets.stream()
                .filter(budget -> now.isAfter(budget.getStartDate()) && now.isBefore(budget.getEndDate()))
                .map(budget -> {
                    Double spentAmount = 0.0;
                    if (budget.getCategory() != null) {
                        List<Expense> categoryExpenses = monthlyExpenses.stream()
                                .filter(e -> e.getCategory() != null && e.getCategory().getId().equals(budget.getCategory().getId()))
                                .collect(Collectors.toList());
                        spentAmount = categoryExpenses.stream().mapToDouble(Expense::getAmount).sum();
                    } else {
                        spentAmount = monthlyExpense;
                    }
                    Double remainingAmount = Math.max(0, budget.getAmount() - spentAmount);
                    Double percentageUsed = budget.getAmount() > 0 ? (spentAmount / budget.getAmount()) * 100 : 0.0;

                    return new BudgetSummaryDTO(
                            budget.getId(),
                            budget.getCategory() != null ? budget.getCategory().getName() : "Overall",
                            budget.getAmount(),
                            spentAmount,
                            remainingAmount,
                            percentageUsed
                    );
                })
                .collect(Collectors.toList());

        // Upcoming bills (credits and subscriptions due in next 30 days)
        LocalDate thirtyDaysFromNow = now.plusDays(30);
        List<Credit> upcomingCredits = creditRepository.findByUserId(userId).stream()
                .filter(credit -> {
                    LocalDate dueDate = credit.getDueDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    return !credit.getPaid() && dueDate.isAfter(now) && dueDate.isBefore(thirtyDaysFromNow);
                })
                .collect(Collectors.toList());

        List<Subscription> upcomingSubscriptions = subscriptionRepository.findByUserId(userId).stream()
                .filter(sub -> sub.getIsActive() && sub.getEndDate() != null)
                .filter(sub -> {
                    LocalDate endDate = sub.getEndDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    return endDate.isAfter(now) && endDate.isBefore(thirtyDaysFromNow);
                })
                .collect(Collectors.toList());

        List<UpcomingBillDTO> upcomingBills = new ArrayList<>();
        upcomingCredits.forEach(credit -> {
            LocalDate dueDate = credit.getDueDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            long daysUntilDue = ChronoUnit.DAYS.between(now, dueDate);
            upcomingBills.add(new UpcomingBillDTO(
                    credit.getId(),
                    "CREDIT",
                    credit.getDescription(),
                    credit.getType(),
                    credit.getAmount(),
                    dueDate.toString(),
                    (int) daysUntilDue
            ));
        });

        upcomingSubscriptions.forEach(sub -> {
            LocalDate endDate = sub.getEndDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            long daysUntilDue = ChronoUnit.DAYS.between(now, endDate);
            upcomingBills.add(new UpcomingBillDTO(
                    sub.getId(),
                    "SUBSCRIPTION",
                    sub.getDescription(),
                    sub.getName(),
                    sub.getPrice(),
                    endDate.toString(),
                    (int) daysUntilDue
            ));
        });

        upcomingBills.sort(Comparator.comparing(UpcomingBillDTO::getDaysUntilDue));

        // Savings progress
        List<Savings> savings = savingsRepository.findByUserId(userId);
        List<SavingsProgressDTO> savingsProgress = savings.stream()
                .map(saving -> {
                    Double progressPercentage = 0.0;
                    if (saving.getTargetAmount() != null && saving.getTargetAmount() > 0) {
                        progressPercentage = (saving.getBalance() / saving.getTargetAmount()) * 100;
                    }
                    String targetDateStr = null;
                    if (saving.getTargetDate() != null) {
                        LocalDate targetDate = saving.getTargetDate().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
                        targetDateStr = targetDate.toString();
                    }
                    return new SavingsProgressDTO(
                            saving.getId(),
                            saving.getName(),
                            saving.getBalance(),
                            saving.getTargetAmount(),
                            progressPercentage,
                            targetDateStr
                    );
                })
                .collect(Collectors.toList());

        return new DashboardResponseDTO(
                totalBalance,
                monthlyIncome,
                monthlyExpense,
                monthlyBalance,
                recentTransactions,
                budgetSummary,
                upcomingBills,
                savingsProgress
        );
    }
}

