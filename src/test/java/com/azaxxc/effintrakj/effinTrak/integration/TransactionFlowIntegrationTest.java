package com.azaxxc.effintrakj.effinTrak.integration;

import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.Category.repo.CategoryRepository;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.NewIncomeRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import com.azaxxc.effintrakj.effinTrak.Income.repo.IncomeRepository;
import com.azaxxc.effintrakj.effinTrak.Income.service.IncomeService;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.accounts.repo.BankAccountRepository;
import com.azaxxc.effintrakj.effinTrak.accounts.service.BankAccountService;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.repo.UserRepository;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import com.azaxxc.effintrakj.effinTrak.util.BaseIntegrationTest;
import com.azaxxc.effintrakj.effinTrak.util.builders.BankAccountTestDataBuilder;
import com.azaxxc.effintrakj.effinTrak.util.builders.CategoryTestDataBuilder;
import com.azaxxc.effintrakj.effinTrak.util.builders.UserTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class TransactionFlowIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private IncomeRepository incomeRepository;

    private User testUser;
    private Category testCategory;
    private BankAccount testBankAccount;

    @BeforeEach
    void setUp() {
        // Create test user
        testUser = UserTestDataBuilder.aUser()
                .withEmail("test@example.com")
                .withPassword("password123")
                .build();
        testUser = userRepository.save(testUser);

        // Create test category
        testCategory = CategoryTestDataBuilder.aCategory()
                .withName("Salary")
                .build();
        testCategory = categoryRepository.save(testCategory);

        // Create test bank account
        testBankAccount = BankAccountTestDataBuilder.aBankAccount()
                .withUser(testUser)
                .withName("Test Bank")
                .withBalance(1000.0)
                .build();
        testBankAccount = bankAccountRepository.save(testBankAccount);
    }

    @Test
    void createIncome_ShouldUpdateBankAccountBalance() {
        // Given
        NewIncomeRequestDTO incomeDTO = new NewIncomeRequestDTO();
        incomeDTO.setDescription("Salary");
        incomeDTO.setAmount(5000.0);
        incomeDTO.setDate(LocalDate.now().toString());
        incomeDTO.setCategoryId(testCategory.getId());
        incomeDTO.setBankAccountId(testBankAccount.getId());
        incomeDTO.setUserId(testUser.getId());
        incomeDTO.setSource("Employer");

        // When
        Income savedIncome = incomeService.saveIncome(incomeDTO, testUser);

        // Then
        assertThat(savedIncome).isNotNull();
        assertThat(savedIncome.getId()).isNotNull();
        assertThat(savedIncome.getAmount()).isEqualTo(5000.0);

        // Verify income was saved
        List<Income> incomes = incomeRepository.findByUserIdOrderByDateDesc(testUser.getId(), 
                org.springframework.data.domain.PageRequest.of(0, 10)).getContent();
        assertThat(incomes).hasSize(1);
        assertThat(incomes.get(0).getAmount()).isEqualTo(5000.0);
    }

    @Test
    void createMultipleIncomes_ShouldRetrieveAll() {
        // Given
        NewIncomeRequestDTO incomeDTO1 = new NewIncomeRequestDTO();
        incomeDTO1.setDescription("Salary 1");
        incomeDTO1.setAmount(1000.0);
        incomeDTO1.setDate(LocalDate.now().toString());
        incomeDTO1.setCategoryId(testCategory.getId());
        incomeDTO1.setBankAccountId(testBankAccount.getId());
        incomeDTO1.setUserId(testUser.getId());

        NewIncomeRequestDTO incomeDTO2 = new NewIncomeRequestDTO();
        incomeDTO2.setDescription("Salary 2");
        incomeDTO2.setAmount(2000.0);
        incomeDTO2.setDate(LocalDate.now().toString());
        incomeDTO2.setCategoryId(testCategory.getId());
        incomeDTO2.setBankAccountId(testBankAccount.getId());
        incomeDTO2.setUserId(testUser.getId());

        // When
        incomeService.saveIncome(incomeDTO1, testUser);
        incomeService.saveIncome(incomeDTO2, testUser);

        // Then
        List<Income> incomes = incomeRepository.findByUserIdOrderByDateDesc(testUser.getId(),
                org.springframework.data.domain.PageRequest.of(0, 10)).getContent();
        assertThat(incomes).hasSize(2);
    }

    @Test
    void createIncome_WithInvalidCategory_ShouldThrowException() {
        // Given
        NewIncomeRequestDTO incomeDTO = new NewIncomeRequestDTO();
        incomeDTO.setDescription("Salary");
        incomeDTO.setAmount(1000.0);
        incomeDTO.setDate(LocalDate.now().toString());
        incomeDTO.setCategoryId(999L); // Non-existent category
        incomeDTO.setBankAccountId(testBankAccount.getId());
        incomeDTO.setUserId(testUser.getId());

        // When & Then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            incomeService.saveIncome(incomeDTO, testUser);
        });
    }
}

