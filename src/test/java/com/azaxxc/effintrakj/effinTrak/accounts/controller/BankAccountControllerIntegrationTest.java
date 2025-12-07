package com.azaxxc.effintrakj.effinTrak.accounts.controller;

import com.azaxxc.effintrakj.effinTrak.accounts.dtos.BankAccountCreateRequestDTO;
import com.azaxxc.effintrakj.effinTrak.accounts.dtos.BankAccountResponseDTO;
import com.azaxxc.effintrakj.effinTrak.accounts.dtos.UpdateBankAccountRequestDTO;
import com.azaxxc.effintrakj.effinTrak.accounts.service.BankAccountService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BankAccountController.class)
class BankAccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BankAccountService bankAccountService;

    @MockBean
    private UserService userService;

    @MockBean
    private GlobalResponseService globalResponseService;

    @MockBean
    private com.azaxxc.effintrakj.effinTrak.globalcomponents.JWTUtil jwtUtil;

    @Test
    @WithMockUser
    void createBankAccount_WithValidData_ShouldReturnSuccess() throws Exception {
        // Given
        BankAccountCreateRequestDTO dto = new BankAccountCreateRequestDTO();
        dto.setBankName("Test Bank");
        dto.setUserId(1L);

        User user = new User();
        user.setId(1L);

        when(userService.findById(1L)).thenReturn(Optional.of(user));
        when(bankAccountService.findByUserId(1L)).thenReturn(Collections.emptyList());
        when(globalResponseService.success(anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(post("/api/bankaccounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getAllBankAccounts_ShouldReturnAccounts() throws Exception {
        // Given
        Long userId = 1L;
        BankAccountResponseDTO accountDTO = new BankAccountResponseDTO();
        accountDTO.setId(1L);
        accountDTO.setName("Test Bank");

        User user = new User();
        user.setId(userId);

        when(userService.findById(userId)).thenReturn(Optional.of(user));
        when(bankAccountService.findByUserId(userId)).thenReturn(List.of(accountDTO));
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(get("/api/bankaccounts/{userId}", userId))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void updateBankAccount_ShouldReturnUpdatedAccount() throws Exception {
        // Given
        Long accountId = 1L;
        UpdateBankAccountRequestDTO dto = new UpdateBankAccountRequestDTO();
        dto.setName("Updated Bank");
        dto.setBalance(2000.0);

        BankAccountResponseDTO updatedAccount = new BankAccountResponseDTO();
        updatedAccount.setId(accountId);
        updatedAccount.setName("Updated Bank");

        when(bankAccountService.updateBankAccount(anyLong(), any(), any())).thenReturn(updatedAccount);
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(put("/api/bankaccounts/{id}", accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void deleteBankAccount_ShouldReturnSuccess() throws Exception {
        // Given
        Long accountId = 1L;

        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(delete("/api/bankaccounts/{id}", accountId))
                .andExpect(status().isOk());
    }
}

