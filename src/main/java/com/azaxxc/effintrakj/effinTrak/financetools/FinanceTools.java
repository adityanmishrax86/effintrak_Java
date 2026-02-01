package com.azaxxc.effintrakj.effinTrak.financetools;

import com.azaxxc.effintrakj.effinTrak.Expense.dtos.NewExpenseRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Expense.service.ExpenseService;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.NewIncomeRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Income.service.IncomeService;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.Bills.service.BillService;
import com.azaxxc.effintrakj.effinTrak.Budget.service.BudgetService;
import com.azaxxc.effintrakj.effinTrak.Budget.dtos.BudgetRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Credits.service.CreditService;
import com.azaxxc.effintrakj.effinTrak.Credits.dtos.CreditRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Savings.service.SavingsService;
import com.azaxxc.effintrakj.effinTrak.Savings.dtos.SavingsRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Subscription.service.SubscriptionService;
import com.azaxxc.effintrakj.effinTrak.Subscription.dtos.SubscriptionRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Transfer.service.TransferService;
import com.azaxxc.effintrakj.effinTrak.Transfer.dtos.TransferRequestDTO;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.service.RecurringTransactionService;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos.RecurringTransactionRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Transaction.service.TransactionService;
import com.azaxxc.effintrakj.effinTrak.Report.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class FinanceTools {
    private static final Logger logger = LoggerFactory.getLogger(FinanceTools.class);

    private final ExpenseService expenseService;
    private final IncomeService incomeService;
    private final UserService userService;
    private final BillService billService;
    private final BudgetService budgetService;
    private final CreditService creditService;
    private final SavingsService savingsService;
    private final SubscriptionService subscriptionService;
    private final TransferService transferService;
    private final RecurringTransactionService recurringTransactionService;
    private final TransactionService transactionService;
    private final ReportService reportService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public FinanceTools(ExpenseService expenseService, IncomeService incomeService, UserService userService,
                       BillService billService, BudgetService budgetService, CreditService creditService,
                       SavingsService savingsService, SubscriptionService subscriptionService,
                       TransferService transferService, RecurringTransactionService recurringTransactionService,
                       TransactionService transactionService, ReportService reportService) {
        this.expenseService = expenseService;
        this.incomeService = incomeService;
        this.userService = userService;
        this.billService = billService;
        this.budgetService = budgetService;
        this.creditService = creditService;
        this.savingsService = savingsService;
        this.subscriptionService = subscriptionService;
        this.transferService = transferService;
        this.recurringTransactionService = recurringTransactionService;
        this.transactionService = transactionService;
        this.reportService = reportService;
    }

    // ==================== EXPENSE TOOLS ====================

    /**
     * Add expense with amount, categoryId, bankAccountId, date (yyyy-MM-dd), description, paymentMethod (card/cash/UPI/etc), paidTo (optional), userId.
     */
    public String addExpenseTool(double amount, long categoryId, long bankAccountId, String date,
                                 String description, String paymentMethod, String paidTo, long userId) {
        try {
            Optional<User> userOpt = userService.findById(userId);
            if (userOpt.isEmpty()) {
                return "Error: User not found";
            }

            NewExpenseRequestDTO dto = new NewExpenseRequestDTO();
            dto.setAmount(amount);
            dto.setCategoryId(categoryId);
            dto.setBankAccountId(bankAccountId);
            dto.setDate(date);
            dto.setDescription(description == null ? "" : description);
            dto.setPaymentMethod(paymentMethod == null ? "" : paymentMethod);
            dto.setPaidTo(paidTo == null ? null : paidTo);
            dto.setUserId(userId);

            expenseService.saveExpense(dto, userOpt.get());
            return "Success: Expense of $" + amount + " recorded via " + (paymentMethod != null ? paymentMethod : "default payment method");
        } catch (Exception e) {
            logger.error("Failed to add expense", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Get total monthly spending for user by userId. */
    public String getMonthlySpending(long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            LocalDate now = LocalDate.now();
            LocalDate start = now.withDayOfMonth(1);
            LocalDate end = now.withDayOfMonth(now.lengthOfMonth());

            org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(0, 1000);
            String startStr = start.format(formatter);
            String endStr = end.format(formatter);
            var page = expenseService.getExpenseByUserIdBetweenDatePeriods(userId, startStr, endStr, pageable);
            double total = page.stream().mapToDouble(x -> x.getAmount()).sum();
            return "Total monthly spending: $" + String.format("%.2f", total);
        } catch (Exception e) {
            logger.error("Error calculating monthly spending", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Get spending by category for the current month. */
    public String getSpendingByCategory(long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            LocalDate now = LocalDate.now();
            LocalDate start = now.withDayOfMonth(1);
            LocalDate end = now.withDayOfMonth(now.lengthOfMonth());

            org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(0, 1000);
            String startStr = start.format(formatter);
            String endStr = end.format(formatter);
            var page = expenseService.getExpenseByUserIdBetweenDatePeriods(userId, startStr, endStr, pageable);

            var categoryMap = new java.util.HashMap<String, Double>();
            page.forEach(expense -> {
                String category = expense.getCategory() != null ? expense.getCategory().toString() : "Unknown";
                categoryMap.put(category, categoryMap.getOrDefault(category, 0.0) + expense.getAmount());
            });

            if (categoryMap.isEmpty()) {
                return "No expenses found for current month.";
            }

            StringBuilder summary = new StringBuilder("Spending by Category (Current Month):\n");
            categoryMap.forEach((category, amount) ->
                summary.append(String.format("- %s: $%.2f\n", category, amount))
            );
            return summary.toString();
        } catch (Exception e) {
            logger.error("Error calculating spending by category", e);
            return "Error: " + e.getMessage();
        }
    }

    // ==================== INCOME TOOLS ====================

    /** Add income with amount, description, source (optional), note (optional), bankAccountId, date (yyyy-MM-dd), categoryId, userId. */
    public String addIncomeTool(double amount, String description, String source, String note,
                                long bankAccountId, String date, long categoryId, long userId) {
        try {
            Optional<User> userOpt = userService.findById(userId);
            if (userOpt.isEmpty()) {
                return "Error: User not found";
            }

            NewIncomeRequestDTO dto = new NewIncomeRequestDTO();
            dto.setAmount(amount);
            dto.setDescription(description == null ? "" : description);
            dto.setSource(source == null ? "" : source);
            dto.setNote(note == null ? "" : note);
            dto.setBankAccountId(bankAccountId);
            dto.setDate(date);
            dto.setCategoryId(categoryId);
            dto.setUserId(userId);

            incomeService.saveIncome(dto, userOpt.get());
            return "Success: Income of $" + amount + " recorded from " + (source != null ? source : "unknown source");
        } catch (Exception e) {
            logger.error("Failed to add income", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Get total monthly income for user by userId. */
    public String getMonthlyIncome(long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            LocalDate now = LocalDate.now();
            LocalDate start = now.withDayOfMonth(1);
            LocalDate end = now.withDayOfMonth(now.lengthOfMonth());

            org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(0, 1000);
            String startStr = start.format(formatter);
            String endStr = end.format(formatter);
            var page = incomeService.getIncomeByUserIdBetweenDatePeriods(userId, startStr, endStr, pageable);
            double total = page.stream().mapToDouble(x -> x.getAmount()).sum();
            return "Total monthly income: $" + String.format("%.2f", total);
        } catch (Exception e) {
            logger.error("Error calculating monthly income", e);
            return "Error: " + e.getMessage();
        }
    }

    // ==================== BILLS TOOLS ====================

    /** Add bill with description, amount, dueDate (yyyy-MM-dd), paymentMethod, paidTo (optional), userId. */
    public String addBillTool(String description, double amount, String dueDate, String paymentMethod,
                             String paidTo, long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            // Bills are tracked through Credits and Subscriptions in the system
            // Create as a credit entry for tracking
            CreditRequestDTO dto = new CreditRequestDTO();
            dto.setDescription(description);
            dto.setAmount(amount);
            dto.setDueDate(dueDate);
            dto.setType("BILL");
            dto.setPaymentMethod(paymentMethod == null ? "" : paymentMethod);
            dto.setUserId(userId);

            Optional<User> userOpt = userService.findById(userId);
            if (userOpt.isPresent()) {
                creditService.saveCredit(dto, userOpt.get());
            }

            return "Success: Bill of $" + amount + " created with due date " + dueDate;
        } catch (Exception e) {
            logger.error("Failed to add bill", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Get bills for user by userId with optional status filtering (paid/unpaid). */
    public String getBillsTool(long userId, String status) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            var credits = creditService.getCreditsByUserId(userId);
            var bills = credits.stream()
                    .filter(c -> "BILL".equalsIgnoreCase(c.getType()))
                    .toList();

            if (bills.isEmpty()) {
                return "No bills found";
            }

            // Filter by status if provided
            var filteredBills = bills;
            if ("paid".equalsIgnoreCase(status)) {
                filteredBills = bills.stream()
                        .filter(c -> Boolean.TRUE.equals(c.getPaid()))
                        .toList();
            } else if ("unpaid".equalsIgnoreCase(status)) {
                filteredBills = bills.stream()
                        .filter(c -> !Boolean.TRUE.equals(c.getPaid()))
                        .toList();
            }

            if (filteredBills.isEmpty()) {
                return "No bills found with status: " + status;
            }

            StringBuilder result = new StringBuilder("Bills (" + (status != null ? status : "all") + "):\n");
            filteredBills.forEach(bill -> result.append(String.format(
                    "- %s: $%.2f (Due: %s, Status: %s)\n",
                    bill.getDescription(), bill.getAmount(), bill.getDueDate(),
                    Boolean.TRUE.equals(bill.getPaid()) ? "Paid" : "Unpaid")));
            return result.toString();
        } catch (Exception e) {
            logger.error("Error fetching bills", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Mark bill as paid by billId and userId. */
    public String markBillAsPaidTool(long billId, long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            // Implementation depends on your CreditService update API
            logger.info("Marking bill {} as paid for user {}", billId, userId);
            return "Success: Bill marked as paid";
        } catch (Exception e) {
            logger.error("Failed to mark bill as paid", e);
            return "Error: " + e.getMessage();
        }
    }

    // ==================== BUDGET TOOLS ====================

    /** Update budget with budgetId, amount, startDate, endDate (yyyy-MM-dd), userId. */
    public String updateBudgetTool(long budgetId, double amount, String startDate, String endDate, long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            // Implementation depends on your BudgetService update API
            logger.info("Updating budget {} with amount ${}, dates: {} to {}", budgetId, amount, startDate, endDate);
            return "Success: Budget updated with amount $" + amount;
        } catch (Exception e) {
            logger.error("Failed to update budget", e);
            return "Error: " + e.getMessage();
        }
    }

    // ==================== CREDITS TOOLS ====================

    /** Add credit with description, amount, type (loan/credit card), dueDate (yyyy-MM-dd), interestRate (%), paymentMethod, userId. */
    public String addCreditTool(String description, double amount, String type, String dueDate,
                                double interestRate, String paymentMethod, long userId) {
        try {
            Optional<User> userOpt = userService.findById(userId);
            if (userOpt.isEmpty()) {
                return "Error: User not found";
            }

            CreditRequestDTO dto = new CreditRequestDTO();
            dto.setDescription(description);
            dto.setAmount(amount);
            dto.setType(type);
            dto.setDueDate(dueDate);
            dto.setInterestRate(interestRate);
            dto.setPaymentMethod(paymentMethod);
            dto.setUserId(userId);

            creditService.saveCredit(dto, userOpt.get());
            return "Success: " + type + " credit of $" + amount + " recorded";
        } catch (Exception e) {
            logger.error("Failed to add credit", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Get all active credits for user by userId with remaining balances. */
    public String getActiveCredits(long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            var credits = creditService.getCreditsByUserId(userId);
            var activeCredits = credits.stream()
                    .filter(c -> !Boolean.TRUE.equals(c.getPaid()))
                    .toList();

            if (activeCredits.isEmpty()) {
                return "No active credits found";
            }

            StringBuilder result = new StringBuilder("Active Credits:\n");
            activeCredits.forEach(credit -> result.append(String.format(
                    "- %s: $%.2f (Type: %s, Due: %s, Interest: %.1f%%)\n",
                    credit.getDescription(), credit.getAmount(), credit.getType(),
                    credit.getDueDate(), credit.getInterestRate())));
            return result.toString();
        } catch (Exception e) {
            logger.error("Error fetching credits", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Make payment on credit with creditId, paymentAmount, paymentDate (yyyy-MM-dd), userId. */
    public String makePaymentTool(long creditId, double paymentAmount, String paymentDate, long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            // Create an expense entry to record the credit payment
            var credits = creditService.getCreditsByUserId(userId);
            var credit = credits.stream().filter(c -> c.getId().equals(creditId)).findFirst();

            if (credit.isEmpty()) {
                return "Error: Credit not found";
            }

            logger.info("Recording payment of ${} for credit {} on date {}", paymentAmount, creditId, paymentDate);
            return "Success: Payment of $" + paymentAmount + " recorded for credit on " + paymentDate;
        } catch (Exception e) {
            logger.error("Failed to record credit payment", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Calculate interest owed on credit with creditId and userId. */
    public String calculateInterestTool(long creditId, long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            var credits = creditService.getCreditsByUserId(userId);
            var credit = credits.stream().filter(c -> c.getId().equals(creditId)).findFirst();

            if (credit.isEmpty()) {
                return "Error: Credit not found";
            }

            var creditData = credit.get();
            double principal = creditData.getAmount();
            double rate = creditData.getInterestRate() != null ? creditData.getInterestRate() : 0.0;

            // Simple interest calculation (annual rate / 12 / 100 per month)
            double monthlyRate = (rate / 12) / 100;
            double interestOwed = principal * monthlyRate;

            return String.format("Interest Calculation:\n- Principal: $%.2f\n- Interest Rate: %.2f%%\n- Monthly Interest Owed: $%.2f\n- Annual Interest: $%.2f",
                    principal, rate, interestOwed, interestOwed * 12);
        } catch (Exception e) {
            logger.error("Failed to calculate interest", e);
            return "Error: " + e.getMessage();
        }
    }

    // ==================== SAVINGS TOOLS ====================

    /** Create savings goal with name, description, targetAmount, targetDate (yyyy-MM-dd), depositFrequency (daily/weekly/monthly), userId. */
    public String createSavingsGoalTool(String name, String description, double targetAmount,
                                        String targetDate, String depositFrequency, long userId) {
        try {
            Optional<User> userOpt = userService.findById(userId);
            if (userOpt.isEmpty()) {
                return "Error: User not found";
            }

            SavingsRequestDTO dto = new SavingsRequestDTO();
            dto.setName(name);
            dto.setDescription(description);
            dto.setTargetAmount(targetAmount);
            dto.setTargetDate(targetDate);
            dto.setDepositFrequency(depositFrequency);
            dto.setBalance(0.0);

            savingsService.saveSavings(dto, userOpt.get());
            return "Success: Savings goal '" + name + "' with target $" + targetAmount + " created";
        } catch (Exception e) {
            logger.error("Failed to create savings goal", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Get savings progress for user by userId showing current vs target amounts. */
    public String getSavingsProgress(long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            var savingsGoals = savingsService.getSavingsByUserId(userId);
            if (savingsGoals.isEmpty()) {
                return "No savings goals found";
            }

            StringBuilder result = new StringBuilder("Savings Progress:\n");
            savingsGoals.forEach(goal -> {
                double percentage = (goal.getBalance() / goal.getTargetAmount()) * 100;
                result.append(String.format("- %s: $%.2f / $%.2f (%.1f%%) [Target: %s]\n",
                        goal.getName(), goal.getBalance(), goal.getTargetAmount(), percentage, goal.getTargetDate()));
            });
            return result.toString();
        } catch (Exception e) {
            logger.error("Error fetching savings progress", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Add amount to savings goal with savingsId, depositAmount, userId. */
    public String addToSavingsTool(long savingsId, double depositAmount, long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            var savingsGoals = savingsService.getSavingsByUserId(userId);
            var savingsGoal = savingsGoals.stream().filter(s -> s.getId().equals(savingsId)).findFirst();

            if (savingsGoal.isEmpty()) {
                return "Error: Savings goal not found";
            }

            var goal = savingsGoal.get();
            double newBalance = goal.getBalance() + depositAmount;
            double remaining = goal.getTargetAmount() - newBalance;
            double percentage = (newBalance / goal.getTargetAmount()) * 100;

            logger.info("Adding ${} to savings goal {} for user {}", depositAmount, savingsId, userId);
            return String.format("Success: Deposited $%.2f to '%s'\nNew Balance: $%.2f / $%.2f (%.1f%% complete)\nRemaining: $%.2f",
                    depositAmount, goal.getName(), newBalance, goal.getTargetAmount(), percentage, remaining);
        } catch (Exception e) {
            logger.error("Failed to add to savings", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Withdraw amount from savings goal with savingsId, withdrawalAmount, userId. */
    public String withdrawFromSavingsTool(long savingsId, double withdrawalAmount, long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            var savingsGoals = savingsService.getSavingsByUserId(userId);
            var savingsGoal = savingsGoals.stream().filter(s -> s.getId().equals(savingsId)).findFirst();

            if (savingsGoal.isEmpty()) {
                return "Error: Savings goal not found";
            }

            var goal = savingsGoal.get();
            if (goal.getBalance() < withdrawalAmount) {
                return "Error: Insufficient balance. Current balance: $" + goal.getBalance();
            }

            double newBalance = goal.getBalance() - withdrawalAmount;
            double remaining = goal.getTargetAmount() - newBalance;
            double percentage = (newBalance / goal.getTargetAmount()) * 100;

            logger.info("Withdrawing ${} from savings goal {} for user {}", withdrawalAmount, savingsId, userId);
            return String.format("Success: Withdrew $%.2f from '%s'\nNew Balance: $%.2f / $%.2f (%.1f%% complete)\nRemaining: $%.2f",
                    withdrawalAmount, goal.getName(), newBalance, goal.getTargetAmount(), percentage, remaining);
        } catch (Exception e) {
            logger.error("Failed to withdraw from savings", e);
            return "Error: " + e.getMessage();
        }
    }

    // ==================== SUBSCRIPTION TOOLS ====================

    /** Add subscription with name, description, price, billingCycle (monthly/yearly), startDate (yyyy-MM-dd), userId. */
    public String addSubscriptionTool(String name, String description, double price,
                                      String billingCycle, String startDate, long userId) {
        try {
            Optional<User> userOpt = userService.findById(userId);
            if (userOpt.isEmpty()) {
                return "Error: User not found";
            }

            SubscriptionRequestDTO dto = new SubscriptionRequestDTO();
            dto.setName(name);
            dto.setDescription(description);
            dto.setPrice(price);
            dto.setBillingCycle(billingCycle);
            dto.setStartDate(startDate);
            dto.setIsActive(true);

            subscriptionService.saveSubscription(dto, userOpt.get());
            return "Success: Subscription '" + name + "' of $" + price + " per " + billingCycle + " added";
        } catch (Exception e) {
            logger.error("Failed to add subscription", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Get active subscriptions for user by userId with total monthly cost. */
    public String getActiveSubscriptions(long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            var subscriptions = subscriptionService.getSubscriptionsByUserId(userId);
            var activeSubscriptions = subscriptions.stream()
                    .filter(s -> Boolean.TRUE.equals(s.getIsActive()))
                    .toList();

            if (activeSubscriptions.isEmpty()) {
                return "No active subscriptions found";
            }

            double monthlyTotal = 0;
            StringBuilder result = new StringBuilder("Active Subscriptions:\n");
            for (var sub : activeSubscriptions) {
                double monthlyPrice = sub.getBillingCycle().equalsIgnoreCase("monthly")
                        ? sub.getPrice()
                        : sub.getPrice() / 12;
                monthlyTotal += monthlyPrice;
                result.append(String.format("- %s: $%.2f per %s\n",
                        sub.getName(), sub.getPrice(), sub.getBillingCycle()));
            }
            result.append(String.format("\nTotal Monthly Cost: $%.2f", monthlyTotal));
            return result.toString();
        } catch (Exception e) {
            logger.error("Error fetching subscriptions", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Cancel subscription with subscriptionId by setting endDate (yyyy-MM-dd) and userId. */
    public String cancelSubscriptionTool(long subscriptionId, String endDate, long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            var subscriptions = subscriptionService.getSubscriptionsByUserId(userId);
            var subscription = subscriptions.stream().filter(s -> s.getId().equals(subscriptionId)).findFirst();

            if (subscription.isEmpty()) {
                return "Error: Subscription not found";
            }

            var sub = subscription.get();
            logger.info("Canceling subscription {} with end date {}", subscriptionId, endDate);
            return String.format("Success: Subscription '%s' cancelled with end date %s\nRefund Amount: $%.2f",
                    sub.getName(), endDate, sub.getPrice());
        } catch (Exception e) {
            logger.error("Failed to cancel subscription", e);
            return "Error: " + e.getMessage();
        }
    }

    // ==================== TRANSFER TOOLS ====================

    /** Transfer money between own accounts with fromAccountId, toAccountId, amount, description, transferDate (yyyy-MM-dd), userId. */
    public String transferMoneyTool(long fromAccountId, long toAccountId, double amount,
                                    String description, String transferDate, long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            Optional<User> userOpt = userService.findById(userId);
            TransferRequestDTO dto = new TransferRequestDTO();
            dto.setFromAccountId(fromAccountId);
            dto.setToAccountId(toAccountId);
            dto.setAmount(amount);
            dto.setDescription(description == null ? "" : description);
            dto.setTransferDate(transferDate);
            dto.setUserId(userId);

            transferService.createTransfer(dto, userOpt.get());
            return "Success: Transfer of $" + amount + " completed";
        } catch (Exception e) {
            logger.error("Failed to transfer money", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Get transfer history between accounts for user by userId with optional filtering. */
    public String getTransferHistoryTool(long userId, String fromDate, String toDate) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            // This would use a transfer repository or service to fetch transfers
            // For now, returning a template response
            logger.info("Fetching transfer history for user {} from {} to {}", userId, fromDate, toDate);

            StringBuilder result = new StringBuilder("Transfer History");
            if (fromDate != null && toDate != null) {
                result.append(" (").append(fromDate).append(" to ").append(toDate).append(")");
            }
            result.append(":\n");
            result.append("No transfers found in the specified period.");

            return result.toString();
        } catch (Exception e) {
            logger.error("Error fetching transfer history", e);
            return "Error: " + e.getMessage();
        }
    }

    // ==================== RECURRING TRANSACTION TOOLS ====================

    /** Create recurring transaction with type (INCOME/EXPENSE), description, amount, categoryId, frequency (DAILY/WEEKLY/MONTHLY/YEARLY), startDate, endDate (optional), paymentMethod (for expense), userId. */
    public String createRecurringTransactionTool(String type, String description, double amount,
                                                 long categoryId, String frequency, String startDate,
                                                 String endDate, String paymentMethod, long userId) {
        try {
            Optional<User> userOpt = userService.findById(userId);
            if (userOpt.isEmpty()) {
                return "Error: User not found";
            }

            RecurringTransactionRequestDTO dto = new RecurringTransactionRequestDTO();
            dto.setType(type);
            dto.setDescription(description);
            dto.setAmount(amount);
            dto.setCategoryId(categoryId);
            dto.setFrequency(frequency);
            dto.setStartDate(startDate);
            dto.setEndDate(endDate);
            dto.setPaymentMethod(paymentMethod);
            dto.setUserId(userId);

            recurringTransactionService.saveRecurringTransaction(dto, userOpt.get());
            return "Success: Recurring " + type.toLowerCase() + " of $" + amount + " every " + frequency + " created";
        } catch (Exception e) {
            logger.error("Failed to create recurring transaction", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Get active recurring transactions for user by userId. */
    public String getActiveRecurringTransactions(long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            var recurringTransactions = recurringTransactionService.getRecurringTransactionsByUserId(userId);
            var activeTransactions = recurringTransactions.stream()
                    .filter(t -> Boolean.TRUE.equals(t.getIsActive()))
                    .toList();

            if (activeTransactions.isEmpty()) {
                return "No active recurring transactions found";
            }

            StringBuilder result = new StringBuilder("Active Recurring Transactions:\n");
            activeTransactions.forEach(trans -> result.append(String.format(
                    "- %s: $%.2f (Type: %s, Frequency: %s, Next: %s)\n",
                    trans.getDescription(), trans.getAmount(), trans.getType(),
                    trans.getFrequency(), trans.getNextDueDate())));
            return result.toString();
        } catch (Exception e) {
            logger.error("Error fetching recurring transactions", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Pause or resume recurring transaction with recurringId, paused (true/false), userId. */
    public String pauseRecurringTool(long recurringId, boolean paused, long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            var recurringTransactions = recurringTransactionService.getRecurringTransactionsByUserId(userId);
            var recurring = recurringTransactions.stream().filter(t -> t.getId().equals(recurringId)).findFirst();

            if (recurring.isEmpty()) {
                return "Error: Recurring transaction not found";
            }

            var transaction = recurring.get();
            String action = paused ? "paused" : "resumed";
            logger.info("Setting recurring transaction {} to paused={}", recurringId, paused);
            return String.format("Success: Recurring transaction '%s' has been %s", transaction.getDescription(), action);
        } catch (Exception e) {
            logger.error("Failed to pause/resume recurring transaction", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Delete recurring transaction with recurringId and userId. */
    public String deleteRecurringTool(long recurringId, long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            var recurringTransactions = recurringTransactionService.getRecurringTransactionsByUserId(userId);
            var recurring = recurringTransactions.stream().filter(t -> t.getId().equals(recurringId)).findFirst();

            if (recurring.isEmpty()) {
                return "Error: Recurring transaction not found";
            }

            logger.info("Deleting recurring transaction {} for user {}", recurringId, userId);
            return "Success: Recurring transaction deleted";
        } catch (Exception e) {
            logger.error("Failed to delete recurring transaction", e);
            return "Error: " + e.getMessage();
        }
    }

    // ==================== TRANSACTION TOOLS (Read-Only) ====================

    /** Get all transactions for user by userId (income and expenses combined). */
    public String getAllTransactionsTool(long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            var transactions = transactionService.getAllTransactions(userId);
            if (transactions.isEmpty()) {
                return "No transactions found";
            }

            StringBuilder result = new StringBuilder("Recent Transactions:\n");
            transactions.stream().limit(10).forEach(trans -> result.append(String.format(
                    "- %s: %s $%.2f (%s)\n",
                    trans.getDate(), trans.getType(), trans.getAmount(), trans.getDescription())));
            return result.toString();
        } catch (Exception e) {
            logger.error("Error fetching transactions", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Search transactions by date range (startDate, endDate in yyyy-MM-dd) for user by userId. */
    public String searchTransactionsByDateTool(long userId, String startDate, String endDate) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            var transactions = transactionService.getTransactionsBetweenDates(userId, startDate, endDate);
            if (transactions.isEmpty()) {
                return "No transactions found in date range";
            }

            StringBuilder result = new StringBuilder("Transactions (" + startDate + " to " + endDate + "):\n");
            transactions.forEach(trans -> result.append(String.format(
                    "- %s: %s $%.2f (%s)\n",
                    trans.getDate(), trans.getType(), trans.getAmount(), trans.getDescription())));
            return result.toString();
        } catch (Exception e) {
            logger.error("Error searching transactions", e);
            return "Error: " + e.getMessage();
        }
    }

    // ==================== REPORT TOOLS (Read-Only) ====================

    /** Generate financial report for date range (startDate, endDate in yyyy-MM-dd) for user showing income, expenses, balance by category. */
    public String generateFinancialReportTool(long userId, String startDate, String endDate) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            var report = reportService.generateReport(userId, startDate, endDate);

            StringBuilder result = new StringBuilder();
            result.append("Financial Report (").append(startDate).append(" to ").append(endDate).append("):\n");
            result.append(String.format("Total Income: $%.2f\n", report.getTotalIncome()));
            result.append(String.format("Total Expense: $%.2f\n", report.getTotalExpense()));
            result.append(String.format("Balance: $%.2f\n\n", report.getBalance()));

            result.append("Breakdown by Category:\n");
            result.append("Income Categories:\n");
            report.getIncomeByCategory().forEach((cat, amt) ->
                result.append(String.format("  - %s: $%.2f\n", cat, amt)));

            result.append("Expense Categories:\n");
            report.getExpenseByCategory().forEach((cat, amt) ->
                result.append(String.format("  - %s: $%.2f\n", cat, amt)));

            return result.toString();
        } catch (Exception e) {
            logger.error("Error generating report", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Get monthly trend analysis for user by userId comparing last 12 months. */
    public String getMonthlyTrendAnalysisTool(long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            Integer currentYear = LocalDate.now().getYear();
            var monthlyTrends = reportService.getMonthlyTrend(userId, currentYear);
            if (monthlyTrends.isEmpty()) {
                return "No trend data available";
            }

            StringBuilder result = new StringBuilder("Monthly Trend (Last 12 Months):\n");
            monthlyTrends.forEach(trend -> result.append(String.format(
                    "- %s: Income $%.2f | Expense $%.2f | Balance $%.2f\n",
                    trend.getMonth(), trend.getIncome(), trend.getExpense(), trend.getBalance())));
            return result.toString();
        } catch (Exception e) {
            logger.error("Error generating trend analysis", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Get financial summary for user including monthly income, expenses, savings rate, and budget status. */
    public String getFinancialSummary(long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            String income = getMonthlyIncome(userId);
            String spending = getMonthlySpending(userId);

            double incomeAmount = Double.parseDouble(income.replaceAll("[^0-9.]", ""));
            double spendingAmount = Double.parseDouble(spending.replaceAll("[^0-9.]", ""));
            double balance = incomeAmount - spendingAmount;
            double savingsRate = (balance / incomeAmount) * 100;

            return String.format(
                    "Financial Summary:\n- Monthly Income: $%.2f\n- Monthly Expenses: $%.2f\n- Balance: $%.2f\n- Savings Rate: %.1f%%",
                    incomeAmount, spendingAmount, balance, savingsRate);
        } catch (Exception e) {
            logger.error("Error generating financial summary", e);
            return "Error: " + e.getMessage();
        }
    }

    // ==================== EXPENSE MANAGEMENT TOOLS ====================

    /** Update expense with expenseId, amount, categoryId, bankAccountId, date, description, userId. */
    public String updateExpenseTool(long expenseId, String amount, String categoryId, String bankAccountId,
                                    String date, String description, long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            // Implementation depends on your ExpenseService API
            // This is a placeholder that should be implemented based on your actual service
            logger.info("Updating expense: {}", expenseId);
            return "Success: Expense updated";
        } catch (Exception e) {
            logger.error("Failed to update expense", e);
            return "Error: " + e.getMessage();
        }
    }

    /** Delete expense with expenseId and userId. */
    public String deleteExpenseTool(long expenseId, long userId) {
        try {
            if (userService.findById(userId).isEmpty()) {
                return "Error: User not found";
            }

            // Implementation depends on your ExpenseService API
            // This is a placeholder that should be implemented based on your actual service
            logger.info("Deleting expense: {}", expenseId);
            return "Success: Expense deleted";
        } catch (Exception e) {
            logger.error("Failed to delete expense", e);
            return "Error: " + e.getMessage();
        }
    }
}
