package com.azaxxc.effintrakj.effinTrak.Transaction.controller;

import com.azaxxc.effintrakj.effinTrak.Transaction.dtos.TransactionResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Transaction.service.TransactionService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.security.AuthenticatedUserResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc(addFilters = false)
class TransactionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private GlobalResponseService globalResponseService;

    @MockBean
    private AuthenticatedUserResolver authenticatedUserResolver;

    @MockBean
    private com.azaxxc.effintrakj.effinTrak.globalcomponents.JWTUtil jwtUtil;

    @Test
    @WithMockUser
    void getAllTransactions_ShouldReturnTransactions() throws Exception {
        // Given
        Long userId = 1L;
        TransactionResponseDTO transactionResponse = new TransactionResponseDTO();

        when(authenticatedUserResolver.resolveRequestedUserId(any(), anyLong())).thenReturn(userId);
        when(transactionService.getAllTransactions(userId)).thenReturn(List.of(transactionResponse));
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(get("/api/transactions/user/{userId}", userId))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getTransactionsBetweenDates_ShouldReturnTransactions() throws Exception {
        // Given
        Long userId = 1L;
        String startDate = "2024-01-01";
        String endDate = "2024-01-31";
        TransactionResponseDTO transactionResponse = new TransactionResponseDTO();

        when(authenticatedUserResolver.resolveRequestedUserId(any(), anyLong())).thenReturn(userId);
        when(transactionService.getTransactionsBetweenDates(anyLong(), anyString(), anyString()))
                .thenReturn(List.of(transactionResponse));
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(get("/api/transactions/user/{userId}/filter", userId)
                        .param("startDate", startDate)
                        .param("endDate", endDate))
                .andExpect(status().isOk());
    }
}
