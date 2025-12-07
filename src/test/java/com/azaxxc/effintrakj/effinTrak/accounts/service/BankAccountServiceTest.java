package com.azaxxc.effintrakj.effinTrak.accounts.service;

import com.azaxxc.effintrakj.effinTrak.accounts.dtos.BankAccountResponseDTO;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.accounts.repo.BankAccountRepository;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.BankAccountMapper;
import com.azaxxc.effintrakj.effinTrak.util.builders.BankAccountTestDataBuilder;
import com.azaxxc.effintrakj.effinTrak.util.builders.UserTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private BankAccountMapper mapper;

    @InjectMocks
    private BankAccountService bankAccountService;

    private BankAccount testBankAccount;
    private BankAccountResponseDTO testBankAccountResponseDTO;

    @BeforeEach
    void setUp() {
        testBankAccount = BankAccountTestDataBuilder.aBankAccount()
                .withId(1L)
                .withName("Test Bank")
                .withBalance(1000.0)
                .withUser(UserTestDataBuilder.aUser().withId(1L).build())
                .build();

        testBankAccountResponseDTO = new BankAccountResponseDTO();
        testBankAccountResponseDTO.setId(1L);
        testBankAccountResponseDTO.setName("Test Bank");
        testBankAccountResponseDTO.setBalance(1000.0);
    }

    @Test
    void saveBankAccount_ShouldSaveAndReturnBankAccount() {
        // Given
        when(bankAccountRepository.save(any(BankAccount.class))).thenReturn(testBankAccount);

        // When
        BankAccount result = bankAccountService.saveBankAccount(testBankAccount);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(testBankAccount.getId());
        verify(bankAccountRepository, times(1)).save(testBankAccount);
    }

    @Test
    void getBankAccountById_WhenExists_ShouldReturnBankAccount() {
        // Given
        when(bankAccountRepository.findById(1L)).thenReturn(Optional.of(testBankAccount));

        // When
        Optional<BankAccount> result = bankAccountService.getBankAccountById(1L);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1L);
        verify(bankAccountRepository, times(1)).findById(1L);
    }

    @Test
    void getBankAccountById_WhenNotExists_ShouldReturnEmpty() {
        // Given
        when(bankAccountRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        Optional<BankAccount> result = bankAccountService.getBankAccountById(999L);

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void findByUserId_ShouldReturnListOfBankAccountDTOs() {
        // Given
        Long userId = 1L;
        when(bankAccountRepository.findByuser_id(userId)).thenReturn(List.of(testBankAccount));
        when(mapper.toResponseDTO(any(BankAccount.class))).thenReturn(testBankAccountResponseDTO);

        // When
        List<BankAccountResponseDTO> result = bankAccountService.findByUserId(userId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        verify(bankAccountRepository, times(1)).findByuser_id(userId);
    }

    @Test
    void updateBankAccount_WithValidId_ShouldUpdateAndReturnDTO() {
        // Given
        Long accountId = 1L;
        String newName = "Updated Bank";
        Double newBalance = 2000.0;
        when(bankAccountRepository.findById(accountId)).thenReturn(Optional.of(testBankAccount));
        when(bankAccountRepository.save(any(BankAccount.class))).thenReturn(testBankAccount);
        when(mapper.toResponseDTO(any(BankAccount.class))).thenReturn(testBankAccountResponseDTO);

        // When
        BankAccountResponseDTO result = bankAccountService.updateBankAccount(accountId, newName, newBalance);

        // Then
        assertThat(result).isNotNull();
        verify(bankAccountRepository, times(1)).findById(accountId);
        verify(bankAccountRepository, times(1)).save(any(BankAccount.class));
    }

    @Test
    void updateBankAccount_WithInvalidId_ShouldThrowException() {
        // Given
        Long accountId = 999L;
        when(bankAccountRepository.findById(accountId)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> bankAccountService.updateBankAccount(accountId, "New Name", 1000.0))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("not found");
    }

    @Test
    void deleteBankAccount_ShouldCallRepository() {
        // Given
        Long accountId = 1L;
        doNothing().when(bankAccountRepository).deleteById(accountId);

        // When
        bankAccountService.deleteBankAccount(accountId);

        // Then
        verify(bankAccountRepository, times(1)).deleteById(accountId);
    }
}

