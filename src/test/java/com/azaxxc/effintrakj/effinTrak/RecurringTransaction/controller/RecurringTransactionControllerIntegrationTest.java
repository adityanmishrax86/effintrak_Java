package com.azaxxc.effintrakj.effinTrak.RecurringTransaction.controller;

import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos.RecurringTransactionRequestDTO;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos.RecurringTransactionResponseDTO;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.service.RecurringTransactionService;
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

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecurringTransactionController.class)
class RecurringTransactionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RecurringTransactionService recurringTransactionService;

    @MockBean
    private UserService userService;

    @MockBean
    private GlobalResponseService globalResponseService;

    @MockBean
    private com.azaxxc.effintrakj.effinTrak.globalcomponents.JWTUtil jwtUtil;

    @Test
    @WithMockUser
    void createRecurringTransaction_WithValidData_ShouldReturnSuccess() throws Exception {
        // Given
        RecurringTransactionRequestDTO dto = new RecurringTransactionRequestDTO();
        dto.setUserId(1L);
        User user = new User();
        user.setId(1L);
        RecurringTransactionResponseDTO response = new RecurringTransactionResponseDTO();

        when(userService.findById(1L)).thenReturn(Optional.of(user));
        when(recurringTransactionService.saveRecurringTransaction(any(RecurringTransactionRequestDTO.class), any(User.class)))
                .thenReturn(response);
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(post("/api/recurring-transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getRecurringTransactionsByUserId_ShouldReturnTransactions() throws Exception {
        // Given
        Long userId = 1L;
        RecurringTransactionResponseDTO transaction = new RecurringTransactionResponseDTO();

        when(recurringTransactionService.getRecurringTransactionsByUserId(userId)).thenReturn(List.of(transaction));
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(get("/api/recurring-transactions/user/{userId}", userId))
                .andExpect(status().isOk());
    }
}

