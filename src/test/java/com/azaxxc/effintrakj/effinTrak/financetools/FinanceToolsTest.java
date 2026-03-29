package com.azaxxc.effintrakj.effinTrak.financetools;

import com.azaxxc.effintrakj.effinTrak.Bills.service.BillService;
import com.azaxxc.effintrakj.effinTrak.Budget.dtos.UpdateBudgetRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Budget.service.BudgetService;
import com.azaxxc.effintrakj.effinTrak.Credits.dtos.CreditResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Credits.dtos.UpdateCreditRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Credits.service.CreditService;
import com.azaxxc.effintrakj.effinTrak.Expense.dtos.ExpenseResponse;
import com.azaxxc.effintrakj.effinTrak.Expense.service.ExpenseService;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.IncomeResponse;
import com.azaxxc.effintrakj.effinTrak.Income.service.IncomeService;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos.RecurringTransactionResponseDTO;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos.UpdateRecurringTransactionRequestDTO;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.service.RecurringTransactionService;
import com.azaxxc.effintrakj.effinTrak.Report.dtos.MonthlyTrendDTO;
import com.azaxxc.effintrakj.effinTrak.Report.dtos.ReportResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Report.service.ReportService;
import com.azaxxc.effintrakj.effinTrak.Savings.dtos.SavingsResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Savings.dtos.UpdateSavingsRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Savings.service.SavingsService;
import com.azaxxc.effintrakj.effinTrak.Subscription.dtos.SubscriptionResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Subscription.dtos.UpdateSubscriptionRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Subscription.service.SubscriptionService;
import com.azaxxc.effintrakj.effinTrak.Transaction.dtos.TransactionResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Transaction.service.TransactionService;
import com.azaxxc.effintrakj.effinTrak.Transfer.dtos.TransferResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Transfer.service.TransferService;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinanceToolsTest {

    @Mock
    private ExpenseService expenseService;
    @Mock
    private IncomeService incomeService;
    @Mock
    private UserService userService;
    @Mock
    private BillService billService;
    @Mock
    private BudgetService budgetService;
    @Mock
    private CreditService creditService;
    @Mock
    private SavingsService savingsService;
    @Mock
    private SubscriptionService subscriptionService;
    @Mock
    private TransferService transferService;
    @Mock
    private RecurringTransactionService recurringTransactionService;
    @Mock
    private TransactionService transactionService;
    @Mock
    private ReportService reportService;

    private FinanceTools financeTools;

    @BeforeEach
    void setUp() {
        financeTools = new FinanceTools(
                expenseService,
                incomeService,
                userService,
                billService,
                budgetService,
                creditService,
                savingsService,
                subscriptionService,
                transferService,
                recurringTransactionService,
                transactionService,
                reportService
        );
        when(userService.findById(anyLong())).thenReturn(Optional.of(user(1L)));
    }

    @Test
    void addExpenseTool_ShouldRecordExpense() {
        String result = financeTools.addExpenseTool(120, 2, 3, "2026-02-14", "Groceries", "CARD", "Store", 1L);

        assertThat(result).contains("Success: Expense");
        verify(expenseService).saveExpense(any(), any(User.class));
    }

    @Test
    void addIncomeTool_ShouldRecordIncome() {
        String result = financeTools.addIncomeTool(2500, "Salary", "Company", null, 3, "2026-02-14", 1, 1L);

        assertThat(result).contains("Success: Income");
        verify(incomeService).saveIncome(any(), any(User.class));
    }

    @Test
    void updateExpenseTool_ShouldUpdate() {
        String result = financeTools.updateExpenseTool(50, "90.0", "2", "3", "2026-02-14", "Updated", 1L);

        assertThat(result).contains("Success: Expense updated");
        verify(expenseService).updateExpenseForUser(1L, 50L, 90.0, 2L, 3L, "2026-02-14", "Updated");
    }

    @Test
    void deleteExpenseTool_ShouldDelete() {
        String result = financeTools.deleteExpenseTool(51L, 1L);

        assertThat(result).contains("Success: Expense deleted");
        verify(expenseService).deleteExpenseForUser(1L, 51L);
    }

    @Test
    void getMonthlySpending_ShouldSummarize() {
        when(expenseService.getExpenseByUserIdBetweenDatePeriods(anyLong(), any(), any(), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(
                        new ExpenseResponse("Food", 30, "Food", "2026-02-01", "CARD", "Main", "Store", false, 1L),
                        new ExpenseResponse("Taxi", 20, "Travel", "2026-02-02", "UPI", "Main", "Cab", false, 2L)
                )));

        String result = financeTools.getMonthlySpending(1L);

        assertThat(result).contains("Total monthly spending: $50.00");
    }

    @Test
    void getMonthlyIncome_ShouldSummarize() {
        when(incomeService.getIncomeByUserIdBetweenDatePeriods(anyLong(), any(), any(), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(
                        new IncomeResponse("Salary", 2000, "Salary", "ACME", "", "Main", "2026-02-01", 1L),
                        new IncomeResponse("Bonus", 500, "Salary", "ACME", "", "Main", "2026-02-05", 2L)
                )));

        String result = financeTools.getMonthlyIncome(1L);

        assertThat(result).contains("Total monthly income: $2500.00");
    }

    @Test
    void getSpendingByCategory_ShouldAggregate() {
        when(expenseService.getExpenseByUserIdBetweenDatePeriods(anyLong(), any(), any(), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(
                        new ExpenseResponse("Food", 30, "Food", "2026-02-01", "CARD", "Main", "Store", false, 1L),
                        new ExpenseResponse("Taxi", 20, "Travel", "2026-02-02", "UPI", "Main", "Cab", false, 2L)
                )));

        String result = financeTools.getSpendingByCategory(1L);

        assertThat(result).contains("Spending by Category");
    }

    @Test
    void createSavingsGoalTool_ShouldCreate() {
        String result = financeTools.createSavingsGoalTool("Trip", "Vacation", 5000, "2026-12-31", "MONTHLY", 1L);

        assertThat(result).contains("Success: Savings goal");
        verify(savingsService).saveSavings(any(), any(User.class));
    }

    @Test
    void getSavingsProgress_ShouldReturnProgress() {
        when(savingsService.getSavingsByUserId(1L)).thenReturn(List.of(
                new SavingsResponseDTO(10L, "Trip", "Vacation", 1000.0, 5000.0, "2026-12-31", "MONTHLY")
        ));

        String result = financeTools.getSavingsProgress(1L);

        assertThat(result).contains("Savings Progress").contains("Trip");
    }

    @Test
    void addToSavingsTool_ShouldUpdateBalance() {
        when(savingsService.getSavingsByUserId(1L)).thenReturn(List.of(
                new SavingsResponseDTO(10L, "Trip", "Vacation", 1000.0, 5000.0, "2026-12-31", "MONTHLY")
        ));

        String result = financeTools.addToSavingsTool(10L, 250.0, 1L);

        assertThat(result).contains("Success: Deposited");
        verify(savingsService).updateSavingsForUser(anyLong(), anyLong(), any(UpdateSavingsRequestDTO.class));
    }

    @Test
    void withdrawFromSavingsTool_ShouldUpdateBalance() {
        when(savingsService.getSavingsByUserId(1L)).thenReturn(List.of(
                new SavingsResponseDTO(10L, "Trip", "Vacation", 1000.0, 5000.0, "2026-12-31", "MONTHLY")
        ));

        String result = financeTools.withdrawFromSavingsTool(10L, 250.0, 1L);

        assertThat(result).contains("Success: Withdrew");
        verify(savingsService).updateSavingsForUser(anyLong(), anyLong(), any(UpdateSavingsRequestDTO.class));
    }

    @Test
    void updateBudgetTool_ShouldUpdate() {
        String result = financeTools.updateBudgetTool(11L, 3000.0, "2026-02-01", "2026-02-28", 1L);

        assertThat(result).contains("Success: Budget updated");
        verify(budgetService).updateBudgetForUser(anyLong(), anyLong(), any(UpdateBudgetRequestDTO.class));
    }

    @Test
    void addSubscriptionTool_ShouldCreate() {
        String result = financeTools.addSubscriptionTool("Netflix", "Streaming", 15.0, "monthly", "2026-02-01", 1L);

        assertThat(result).contains("Success: Subscription");
        verify(subscriptionService).saveSubscription(any(), any(User.class));
    }

    @Test
    void getActiveSubscriptions_ShouldList() {
        when(subscriptionService.getSubscriptionsByUserId(1L)).thenReturn(List.of(
                new SubscriptionResponseDTO(1L, "Netflix", "Streaming", 15.0, "monthly", "2026-01-01", null, true),
                new SubscriptionResponseDTO(2L, "Inactive", "", 8.0, "monthly", "2026-01-01", "2026-02-01", false)
        ));

        String result = financeTools.getActiveSubscriptions(1L);

        assertThat(result).contains("Active Subscriptions").contains("Netflix");
    }

    @Test
    void cancelSubscriptionTool_ShouldDeactivate() {
        when(subscriptionService.getSubscriptionsByUserId(1L)).thenReturn(List.of(
                new SubscriptionResponseDTO(1L, "Netflix", "Streaming", 15.0, "monthly", "2026-01-01", null, true)
        ));

        String result = financeTools.cancelSubscriptionTool(1L, "2026-03-01", 1L);

        assertThat(result).contains("cancelled");
        verify(subscriptionService).updateSubscriptionForUser(anyLong(), anyLong(), any(UpdateSubscriptionRequestDTO.class));
    }

    @Test
    void addCreditTool_ShouldCreate() {
        String result = financeTools.addCreditTool("Card bill", 200.0, "BILL", "2026-03-01", 1.2, "CARD", 1L);

        assertThat(result).contains("Success:");
        verify(creditService).saveCredit(any(), any(User.class));
    }

    @Test
    void getActiveCredits_ShouldList() {
        when(creditService.getCreditsByUserId(1L)).thenReturn(List.of(
                new CreditResponseDTO(1L, "Card bill", 200.0, "2026-03-01", null, "BILL", 1.2, "CARD", false),
                new CreditResponseDTO(2L, "Closed", 150.0, "2026-03-15", null, "LOAN", 1.0, "CARD", true)
        ));

        String result = financeTools.getActiveCredits(1L);

        assertThat(result).contains("Active Credits").contains("Card bill");
    }

    @Test
    void makePaymentTool_ShouldUpdateCredit() {
        when(creditService.getCreditsByUserId(1L)).thenReturn(List.of(
                new CreditResponseDTO(1L, "Card bill", 200.0, "2026-03-01", null, "BILL", 1.2, "CARD", false)
        ));

        String result = financeTools.makePaymentTool(1L, 50.0, "2026-02-14", 1L);

        assertThat(result).contains("Success: Payment");
        verify(creditService).updateCreditForUser(anyLong(), anyLong(), any(UpdateCreditRequestDTO.class));
    }

    @Test
    void transferMoneyTool_ShouldTransfer() {
        String result = financeTools.transferMoneyTool(1L, 2L, 75.0, "Move funds", "2026-02-14", 1L);

        assertThat(result).contains("Success: Transfer");
        verify(transferService).createTransfer(any(), any(User.class));
    }

    @Test
    void createRecurringTransactionTool_ShouldCreate() {
        String result = financeTools.createRecurringTransactionTool(
                "EXPENSE", "Rent", 1200.0, 2L, "MONTHLY", "2026-02-01", null, "BANK", 1L);

        assertThat(result).contains("Success: Recurring");
        verify(recurringTransactionService).saveRecurringTransaction(any(), any(User.class));
    }

    @Test
    void getActiveRecurringTransactions_ShouldList() {
        when(recurringTransactionService.getRecurringTransactionsByUserId(1L)).thenReturn(List.of(
                new RecurringTransactionResponseDTO(1L, "Rent", 1200.0, "EXPENSE", "Housing", "Main",
                        "MONTHLY", "2026-02-01", null, "2026-03-01", "BANK", "", "", "", true)
        ));

        String result = financeTools.getActiveRecurringTransactions(1L);

        assertThat(result).contains("Active Recurring Transactions").contains("Rent");
    }

    @Test
    void pauseRecurringTool_ShouldToggleActive() {
        when(recurringTransactionService.getRecurringTransactionsByUserId(1L)).thenReturn(List.of(
                new RecurringTransactionResponseDTO(1L, "Rent", 1200.0, "EXPENSE", "Housing", "Main",
                        "MONTHLY", "2026-02-01", null, "2026-03-01", "BANK", "", "", "", true)
        ));

        String result = financeTools.pauseRecurringTool(1L, true, 1L);

        assertThat(result).contains("paused");
        verify(recurringTransactionService).updateRecurringTransactionForUser(anyLong(), anyLong(), any(UpdateRecurringTransactionRequestDTO.class));
    }

    @Test
    void deleteRecurringTool_ShouldDelete() {
        when(recurringTransactionService.getRecurringTransactionsByUserId(1L)).thenReturn(List.of(
                new RecurringTransactionResponseDTO(1L, "Rent", 1200.0, "EXPENSE", "Housing", "Main",
                        "MONTHLY", "2026-02-01", null, "2026-03-01", "BANK", "", "", "", true)
        ));

        String result = financeTools.deleteRecurringTool(1L, 1L);

        assertThat(result).contains("Success: Recurring transaction deleted");
        verify(recurringTransactionService).deleteRecurringTransactionForUser(1L, 1L);
    }

    @Test
    void queryFinancialDataTool_ShouldHandleReportType() {
        when(reportService.generateReport(1L, "2026-01-01", "2026-01-31"))
                .thenReturn(new ReportResponseDTO(
                        3000.0, 1200.0, 1800.0,
                        Map.of("Salary", 3000.0),
                        Map.of("Food", 500.0)
                ));

        String result = financeTools.queryFinancialDataTool(1L, "REPORT", "2026-01-01", "2026-01-31", null);

        assertThat(result).contains("Financial Report").contains("Total Income");
    }

    @Test
    void queryFinancialDataTool_ShouldHandleTransactionSearch() {
        when(transactionService.searchTransactions(1L, "uber")).thenReturn(List.of(
                new TransactionResponseDTO(1L, "Uber ride", 23.0, "2026-02-10", "EXPENSE", "Transport", "Uber")
        ));

        String result = financeTools.queryFinancialDataTool(1L, "TRANSACTION_SEARCH", "2026-02-01", "2026-02-28", "uber");

        assertThat(result).contains("Transactions matching 'uber'").contains("Uber ride");
    }

    @Test
    void queryFinancialDataTool_ShouldHandleTopSpendingCategories() {
        when(transactionService.getTransactionsBetweenDates(1L, "2026-02-01", "2026-02-28")).thenReturn(List.of(
                new TransactionResponseDTO(1L, "Food", 30.0, "2026-02-10", "EXPENSE", "Food", "Store"),
                new TransactionResponseDTO(2L, "Cab", 15.0, "2026-02-11", "EXPENSE", "Travel", "Cab"),
                new TransactionResponseDTO(3L, "Lunch", 20.0, "2026-02-12", "EXPENSE", "Food", "Cafe")
        ));

        String result = financeTools.queryFinancialDataTool(1L, "TOP_SPENDING_CATEGORIES", "2026-02-01", "2026-02-28", null);

        assertThat(result).contains("Top Spending Categories").contains("Food: $50.00");
    }

    @Test
    void getFinancialSummary_ShouldCombineIncomeAndSpending() {
        when(incomeService.getIncomeByUserIdBetweenDatePeriods(anyLong(), any(), any(), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(
                        new IncomeResponse("Salary", 3000, "Salary", "ACME", "", "Main", "2026-02-01", 1L)
                )));
        when(expenseService.getExpenseByUserIdBetweenDatePeriods(anyLong(), any(), any(), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(
                        new ExpenseResponse("Food", 1200, "Food", "2026-02-01", "CARD", "Main", "Store", false, 1L)
                )));

        String result = financeTools.getFinancialSummary(1L);

        assertThat(result).contains("Financial Summary").contains("Savings Rate");
    }

    @Test
    void getFinancialSummary_WhenIncomeIsZero_ShouldNotReturnInfinity() {
        when(incomeService.getIncomeByUserIdBetweenDatePeriods(anyLong(), any(), any(), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of()));
        when(expenseService.getExpenseByUserIdBetweenDatePeriods(anyLong(), any(), any(), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(
                        new ExpenseResponse("Food", 100, "Food", "2026-02-01", "CARD", "Main", "Store", false, 1L)
                )));

        String result = financeTools.getFinancialSummary(1L);

        assertThat(result).contains("Savings Rate: 0.0%");
        assertThat(result).doesNotContain("Infinity");
    }

    @Test
    void getMonthlyTrendAnalysisTool_ShouldRenderTrends() {
        when(reportService.getMonthlyTrend(1L, LocalDate.now().getYear())).thenReturn(List.of(
                new MonthlyTrendDTO("2026-01", 3000.0, 1500.0, 1500.0)
        ));

        String result = financeTools.getMonthlyTrendAnalysisTool(1L);

        assertThat(result).contains("Monthly Trend");
    }

    @Test
    void getTransferHistoryTool_ShouldRenderTransfers() {
        when(transferService.getTransfersByUserId(1L)).thenReturn(List.of(
                new TransferResponseDTO(1L, 90.0, "Wallet topup", "2026-02-14", "Main", "Wallet")
        ));

        String result = financeTools.getTransferHistoryTool(1L, null, null);

        assertThat(result).contains("Transfer History").contains("Wallet topup");
    }

    @Test
    void markBillAsPaidTool_ShouldUpdateCreditPaid() {
        String result = financeTools.markBillAsPaidTool(11L, 1L);

        assertThat(result).contains("Success: Bill marked as paid");
        ArgumentCaptor<UpdateCreditRequestDTO> captor = ArgumentCaptor.forClass(UpdateCreditRequestDTO.class);
        verify(creditService).updateCreditForUser(eq(1L), eq(11L), captor.capture());
        assertThat(captor.getValue().getPaid()).isTrue();
    }

    private User user(Long id) {
        User user = new User();
        user.setId(id);
        user.setEmail("test@example.com");
        return user;
    }
}
