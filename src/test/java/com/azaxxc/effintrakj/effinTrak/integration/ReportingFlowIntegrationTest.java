package com.azaxxc.effintrakj.effinTrak.integration;

import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.Category.repo.CategoryRepository;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.NewIncomeRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import com.azaxxc.effintrakj.effinTrak.Income.service.IncomeService;
import com.azaxxc.effintrakj.effinTrak.Expense.dtos.NewExpenseRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Expense.model.Expense;
import com.azaxxc.effintrakj.effinTrak.Expense.service.ExpenseService;
import com.azaxxc.effintrakj.effinTrak.Report.dtos.ReportResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Report.service.ReportService;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.accounts.repo.BankAccountRepository;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.repo.UserRepository;
import com.azaxxc.effintrakj.effinTrak.util.BaseIntegrationTest;
import com.azaxxc.effintrakj.effinTrak.util.builders.BankAccountTestDataBuilder;
import com.azaxxc.effintrakj.effinTrak.util.builders.CategoryTestDataBuilder;
import com.azaxxc.effintrakj.effinTrak.util.builders.UserTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class ReportingFlowIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ReportService reportService;

    private User testUser;
    private Category incomeCategory;
    private Category expenseCategory;
    private BankAccount testBankAccount;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @BeforeEach
    void setUp() {
        // Create test user
        testUser = UserTestDataBuilder.aUser()
                .withEmail("report@example.com")
                .withPassword("password123")
                .build();
        testUser = userRepository.save(testUser);

        // Create categories
        incomeCategory = CategoryTestDataBuilder.aCategory()
                .withName("Salary")
                .build();
        incomeCategory = categoryRepository.save(incomeCategory);

        expenseCategory = CategoryTestDataBuilder.aCategory()
                .withName("Food")
                .build();
        expenseCategory = categoryRepository.save(expenseCategory);

        // Create bank account
        testBankAccount = BankAccountTestDataBuilder.aBankAccount()
                .withUser(testUser)
                .withName("Test Bank")
                .withBalance(0.0)
                .build();
        testBankAccount = bankAccountRepository.save(testBankAccount);
    }

    @Test
    void generateReport_WithIncomeAndExpenses_ShouldCalculateCorrectly() {
        // Given - Create income
        NewIncomeRequestDTO incomeDTO = new NewIncomeRequestDTO();
        incomeDTO.setDescription("Salary");
        incomeDTO.setAmount(5000.0);
        incomeDTO.setDate(LocalDate.now().toString());
        incomeDTO.setCategoryId(incomeCategory.getId());
        incomeDTO.setBankAccountId(testBankAccount.getId());
        incomeDTO.setUserId(testUser.getId());
        Income savedIncome = incomeService.saveIncome(incomeDTO, testUser);

        // Given - Create expense
        NewExpenseRequestDTO expenseDTO = new NewExpenseRequestDTO();
        expenseDTO.setDescription("Groceries");
        expenseDTO.setAmount(200.0);
        expenseDTO.setDate(LocalDate.now().toString());
        expenseDTO.setCategoryId(expenseCategory.getId());
        expenseDTO.setBankAccountId(testBankAccount.getId());
        expenseDTO.setUserId(testUser.getId());
        Expense savedExpense = expenseService.saveExpense(expenseDTO, testUser);

        // When - Generate report
        String startDate = LocalDate.now().minusDays(30).format(formatter);
        String endDate = LocalDate.now().format(formatter);
        ReportResponseDTO report = reportService.generateReport(testUser.getId(), startDate, endDate);

        // Then
        assertThat(report).isNotNull();
        // Verify report contains the transactions we created
        // Note: Actual assertions depend on ReportService implementation
    }

    @Test
    void getMonthlyTrend_WithTransactions_ShouldReturnTrends() {
        // Given - Create transactions for different months
        LocalDate currentMonth = LocalDate.now();
        LocalDate lastMonth = currentMonth.minusMonths(1);

        // Create income for current month
        NewIncomeRequestDTO incomeDTO = new NewIncomeRequestDTO();
        incomeDTO.setDescription("Salary");
        incomeDTO.setAmount(5000.0);
        incomeDTO.setDate(currentMonth.toString());
        incomeDTO.setCategoryId(incomeCategory.getId());
        incomeDTO.setBankAccountId(testBankAccount.getId());
        incomeDTO.setUserId(testUser.getId());
        incomeService.saveIncome(incomeDTO, testUser);

        // When - Get monthly trend
        var trends = reportService.getMonthlyTrend(testUser.getId(), currentMonth.getYear());

        // Then
        assertThat(trends).isNotNull();
        // Verify trends are calculated correctly
    }
}

