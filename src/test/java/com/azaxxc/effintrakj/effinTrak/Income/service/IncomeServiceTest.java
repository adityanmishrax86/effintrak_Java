package com.azaxxc.effintrakj.effinTrak.Income.service;

import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.Category.service.CategoryService;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.IncomeResponse;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.NewIncomeRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.UpdateIncomeRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import com.azaxxc.effintrakj.effinTrak.Income.repo.IncomeRepository;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.accounts.service.BankAccountService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.IncomeMapper;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import com.azaxxc.effintrakj.effinTrak.util.builders.BankAccountTestDataBuilder;
import com.azaxxc.effintrakj.effinTrak.util.builders.CategoryTestDataBuilder;
import com.azaxxc.effintrakj.effinTrak.util.builders.IncomeTestDataBuilder;
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
class IncomeServiceTest {

    @Mock
    private IncomeRepository incomeRepository;

    @Mock
    private CategoryService categoryService;

    @Mock
    private BankAccountService bankAccountService;

    @Mock
    private UserService userService;

    @Mock
    private IncomeMapper mapper;

    @InjectMocks
    private IncomeService incomeService;

    private User testUser;
    private Category testCategory;
    private BankAccount testBankAccount;
    private Income testIncome;
    private NewIncomeRequestDTO newIncomeRequestDTO;

    @BeforeEach
    void setUp() {
        testUser = UserTestDataBuilder.aUser().withId(1L).build();
        testCategory = CategoryTestDataBuilder.aCategory().withId(1L).withName("Salary").build();
        testBankAccount = BankAccountTestDataBuilder.aBankAccount()
                .withId(1L)
                .withUser(testUser)
                .withBalance(1000.0)
                .build();
        testIncome = IncomeTestDataBuilder.anIncome()
                .withId(1L)
                .withUser(testUser)
                .withCategory(testCategory)
                .withBankAccount(testBankAccount)
                .withAmount(1000.0)
                .withDate(LocalDate.now())
                .build();

        newIncomeRequestDTO = new NewIncomeRequestDTO();
        newIncomeRequestDTO.setDescription("Test Income");
        newIncomeRequestDTO.setAmount(1000.0);
        newIncomeRequestDTO.setDate(LocalDate.now().toString());
        newIncomeRequestDTO.setCategoryId(1L);
        newIncomeRequestDTO.setBankAccountId(1L);
        newIncomeRequestDTO.setSource("Salary");
        newIncomeRequestDTO.setNote("Test note");
    }

    @Test
    void saveIncome_WithValidData_ShouldSaveIncome() {
        // Given
        when(categoryService.getCategoryById(1L)).thenReturn(Optional.of(testCategory));
        when(bankAccountService.getBankAccountById(1L)).thenReturn(Optional.of(testBankAccount));
        when(mapper.toIncome(any(NewIncomeRequestDTO.class))).thenReturn(testIncome);
        when(incomeRepository.save(any(Income.class))).thenReturn(testIncome);

        // When
        Income result = incomeService.saveIncome(newIncomeRequestDTO, testUser);

        // Then
        assertThat(result).isNotNull();
        verify(categoryService, times(1)).getCategoryById(1L);
        verify(bankAccountService, times(1)).getBankAccountById(1L);
        verify(incomeRepository, times(1)).save(any(Income.class));
    }

    @Test
    void saveIncome_WithInvalidAmount_ShouldThrowException() {
        // Given
        newIncomeRequestDTO.setAmount(0.0);

        // When & Then
        assertThatThrownBy(() -> incomeService.saveIncome(newIncomeRequestDTO, testUser))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("amount must be greater than zero");
        verify(incomeRepository, never()).save(any(Income.class));
    }

    @Test
    void saveIncome_WithEmptyDescription_ShouldThrowException() {
        // Given
        newIncomeRequestDTO.setDescription("");

        // When & Then
        assertThatThrownBy(() -> incomeService.saveIncome(newIncomeRequestDTO, testUser))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("required fields");
        verify(incomeRepository, never()).save(any(Income.class));
    }

    @Test
    void saveIncome_WithInvalidCategory_ShouldThrowException() {
        // Given
        when(categoryService.getCategoryById(anyLong())).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> incomeService.saveIncome(newIncomeRequestDTO, testUser))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No Category found");
        verify(incomeRepository, never()).save(any(Income.class));
    }

    @Test
    void getIncomeByUserId_ShouldReturnPageOfIncomes() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Income> incomePage = new PageImpl<>(List.of(testIncome), pageable, 1);
        IncomeResponse incomeResponse = new IncomeResponse("Test", 1000.0, "Category", "Source", "Note", "Bank", "2024-01-01", 1L);
        when(incomeRepository.findByUserIdOrderByDateDesc(anyLong(), any(Pageable.class)))
                .thenReturn(incomePage);
        when(mapper.toIncomeResponse(any(Income.class))).thenReturn(incomeResponse);

        // When
        Page<IncomeResponse> result = incomeService.getIncomeByUserId(1L, pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        verify(incomeRepository, times(1)).findByUserIdOrderByDateDesc(1L, pageable);
    }

    @Test
    void getIncomeByUserId_WhenNoIncomes_ShouldReturnEmptyPage() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Income> emptyPage = new PageImpl<>(Collections.emptyList(), pageable, 0);
        when(incomeRepository.findByUserIdOrderByDateDesc(anyLong(), any(Pageable.class)))
                .thenReturn(emptyPage);

        // When
        Page<IncomeResponse> result = incomeService.getIncomeByUserId(1L, pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).isEmpty();
    }

    @Test
    void deleteIncome_ShouldCallRepository() {
        // Given
        Long incomeId = 1L;
        doNothing().when(incomeRepository).deleteById(incomeId);

        // When
        incomeService.deleteIncome(incomeId);

        // Then
        verify(incomeRepository, times(1)).deleteById(incomeId);
    }
}

