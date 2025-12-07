package com.azaxxc.effintrakj.effinTrak.Expense.service;

import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.Category.service.CategoryService;
import com.azaxxc.effintrakj.effinTrak.Expense.dtos.ExpenseResponse;
import com.azaxxc.effintrakj.effinTrak.Expense.dtos.NewExpenseRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Expense.dtos.UpdateExpenseRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Expense.model.Expense;
import com.azaxxc.effintrakj.effinTrak.Expense.repo.ExpenseRepository;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.accounts.service.BankAccountService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.ExpenseMapper;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import com.azaxxc.effintrakj.effinTrak.util.builders.BankAccountTestDataBuilder;
import com.azaxxc.effintrakj.effinTrak.util.builders.CategoryTestDataBuilder;
import com.azaxxc.effintrakj.effinTrak.util.builders.ExpenseTestDataBuilder;
import com.azaxxc.effintrakj.effinTrak.util.builders.UserTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private CategoryService categoryService;

    @Mock
    private BankAccountService bankAccountService;

    @Mock
    private UserService userService;

    @Mock
    private ExpenseMapper mapper;

    @InjectMocks
    private ExpenseService expenseService;

    private User testUser;
    private Category testCategory;
    private BankAccount testBankAccount;
    private Expense testExpense;
    private NewExpenseRequestDTO newExpenseRequestDTO;

    @BeforeEach
    void setUp() {
        testUser = UserTestDataBuilder.aUser().withId(1L).build();
        testCategory = CategoryTestDataBuilder.aCategory().withId(1L).withName("Food").build();
        testBankAccount = BankAccountTestDataBuilder.aBankAccount()
                .withId(1L)
                .withUser(testUser)
                .withBalance(1000.0)
                .build();
        testExpense = ExpenseTestDataBuilder.anExpense()
                .withId(1L)
                .withUser(testUser)
                .withCategory(testCategory)
                .withBankAccount(testBankAccount)
                .withAmount(50.0)
                .withDate(LocalDate.now())
                .build();

        newExpenseRequestDTO = new NewExpenseRequestDTO();
        newExpenseRequestDTO.setDescription("Test Expense");
        newExpenseRequestDTO.setAmount(50.0);
        newExpenseRequestDTO.setDate(LocalDate.now().toString());
        newExpenseRequestDTO.setCategoryId(1L);
        newExpenseRequestDTO.setBankAccountId(1L);
        newExpenseRequestDTO.setPaymentMethod("Credit Card");
        newExpenseRequestDTO.setPaidTo("Test Merchant");
        newExpenseRequestDTO.setRecurring(false);
    }

    @Test
    void saveExpense_WithValidData_ShouldSaveExpense() {
        // Given
        when(categoryService.getCategories()).thenReturn(List.of(testCategory));
        when(bankAccountService.getBankAccountById(1L)).thenReturn(Optional.of(testBankAccount));
        when(mapper.toExpense(any(NewExpenseRequestDTO.class))).thenReturn(testExpense);
        when(expenseRepository.save(any(Expense.class))).thenReturn(testExpense);

        // When
        Expense result = expenseService.saveExpense(newExpenseRequestDTO, testUser);

        // Then
        assertThat(result).isNotNull();
        verify(categoryService, times(1)).getCategories();
        verify(bankAccountService, times(1)).getBankAccountById(1L);
        verify(expenseRepository, times(1)).save(any(Expense.class));
    }

    @Test
    void saveExpense_WithInvalidAmount_ShouldThrowException() {
        // Given
        newExpenseRequestDTO.setAmount(0.0);

        // When & Then
        assertThatThrownBy(() -> expenseService.saveExpense(newExpenseRequestDTO, testUser))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("amount must be greater than zero");
        verify(expenseRepository, never()).save(any(Expense.class));
    }

    @Test
    void saveExpense_WithEmptyDescription_ShouldThrowException() {
        // Given
        newExpenseRequestDTO.setDescription("");

        // When & Then
        assertThatThrownBy(() -> expenseService.saveExpense(newExpenseRequestDTO, testUser))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("required fields");
        verify(expenseRepository, never()).save(any(Expense.class));
    }

    @Test
    void saveExpense_WithInvalidCategory_ShouldThrowException() {
        // Given
        when(categoryService.getCategories()).thenReturn(Collections.emptyList());

        // When & Then
        assertThatThrownBy(() -> expenseService.saveExpense(newExpenseRequestDTO, testUser))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No Category found");
        verify(expenseRepository, never()).save(any(Expense.class));
    }

    @Test
    void getExpenseByUserId_ShouldReturnPageOfExpenses() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Expense> expensePage = new PageImpl<>(List.of(testExpense), pageable, 1);
        ExpenseResponse expenseResponse = new ExpenseResponse("Test", 50.0, "Category", "2024-01-01", "Credit", "Bank", "Merchant", false, 1L);
        when(expenseRepository.findByUserIdOrderByDateDesc(anyLong(), any(Pageable.class)))
                .thenReturn(expensePage);
        when(mapper.toExpenseResponse(any(Expense.class))).thenReturn(expenseResponse);

        // When
        Page<ExpenseResponse> result = expenseService.getExpenseByUserId(1L, pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        verify(expenseRepository, times(1)).findByUserIdOrderByDateDesc(1L, pageable);
    }

    @Test
    void deleteExpense_ShouldCallRepository() {
        // Given
        Long expenseId = 1L;
        doNothing().when(expenseRepository).deleteById(expenseId);

        // When
        expenseService.deleteExpense(expenseId);

        // Then
        verify(expenseRepository, times(1)).deleteById(expenseId);
    }
}

