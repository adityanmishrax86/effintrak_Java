package com.azaxxc.effintrakj.effinTrak.Expense.controller;

import com.azaxxc.effintrakj.effinTrak.Expense.dtos.ExpenseResponse;
import com.azaxxc.effintrakj.effinTrak.Expense.dtos.NewExpenseRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Expense.model.Expense;
import com.azaxxc.effintrakj.effinTrak.Expense.service.ExpenseService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExpenseController.class)
class ExpenseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ExpenseService expenseService;

    @MockBean
    private UserService userService;

    @MockBean
    private GlobalResponseService globalResponseService;

    @MockBean
    private com.azaxxc.effintrakj.effinTrak.globalcomponents.JWTUtil jwtUtil;

    @Test
    @WithMockUser
    void createExpense_WithValidData_ShouldReturnSuccess() throws Exception {
        // Given
        NewExpenseRequestDTO dto = new NewExpenseRequestDTO();
        dto.setDescription("Test Expense");
        dto.setAmount(50.0);
        dto.setDate(LocalDate.now().toString());
        dto.setCategoryId(1L);
        dto.setBankAccountId(1L);
        dto.setUserId(1L);

        User user = new User();
        user.setId(1L);
        Expense savedExpense = new Expense();
        savedExpense.setId(1L);

        when(userService.findById(1L)).thenReturn(Optional.of(user));
        when(expenseService.saveExpense(any(NewExpenseRequestDTO.class), any(User.class))).thenReturn(savedExpense);
        when(globalResponseService.success(anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(post("/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getExpenseByUserId_ShouldReturnExpenses() throws Exception {
        // Given
        Long userId = 1L;
        PageRequest pageable = PageRequest.of(0, 10);
        ExpenseResponse expenseResponse = new ExpenseResponse("Test", 50.0, "Category", "2024-01-01", "Credit", "Bank", "Merchant", false, 1L);
        Page<ExpenseResponse> expensePage = new PageImpl<>(List.of(expenseResponse), pageable, 1);

        when(expenseService.getExpenseByUserId(userId, pageable)).thenReturn(expensePage);
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(get("/api/expenses/user/{userId}", userId)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void deleteExpense_ShouldReturnNoContent() throws Exception {
        // Given
        Long expenseId = 1L;

        // When & Then
        mockMvc.perform(delete("/api/expenses/{id}", expenseId))
                .andExpect(status().isNoContent());
    }
}

