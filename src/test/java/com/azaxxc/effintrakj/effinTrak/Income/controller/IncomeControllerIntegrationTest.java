package com.azaxxc.effintrakj.effinTrak.Income.controller;

import com.azaxxc.effintrakj.effinTrak.Income.dtos.IncomeResponse;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.NewIncomeRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import com.azaxxc.effintrakj.effinTrak.Income.service.IncomeService;
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

@WebMvcTest(IncomeController.class)
class IncomeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IncomeService incomeService;

    @MockBean
    private UserService userService;

    @MockBean
    private GlobalResponseService globalResponseService;

    @MockBean
    private com.azaxxc.effintrakj.effinTrak.globalcomponents.JWTUtil jwtUtil;

    @Test
    @WithMockUser
    void createIncome_WithValidData_ShouldReturnSuccess() throws Exception {
        // Given
        NewIncomeRequestDTO dto = new NewIncomeRequestDTO();
        dto.setDescription("Test Income");
        dto.setAmount(1000.0);
        dto.setDate(LocalDate.now().toString());
        dto.setCategoryId(1L);
        dto.setBankAccountId(1L);
        dto.setUserId(1L);

        User user = new User();
        user.setId(1L);
        Income savedIncome = new Income();
        savedIncome.setId(1L);

        when(userService.findById(1L)).thenReturn(Optional.of(user));
        when(incomeService.saveIncome(any(NewIncomeRequestDTO.class), any(User.class))).thenReturn(savedIncome);
        when(globalResponseService.success(anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(post("/api/incomes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getIncomeByUserId_ShouldReturnIncomes() throws Exception {
        // Given
        Long userId = 1L;
        PageRequest pageable = PageRequest.of(0, 10);
        IncomeResponse incomeResponse = new IncomeResponse("Test", 1000.0, "Category", "Source", "Note", "Bank", "2024-01-01", 1L);
        Page<IncomeResponse> incomePage = new PageImpl<>(List.of(incomeResponse), pageable, 1);

        when(incomeService.getIncomeByUserId(userId, pageable)).thenReturn(incomePage);
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(get("/api/incomes/user/{userId}", userId)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void deleteIncome_ShouldReturnNoContent() throws Exception {
        // Given
        Long incomeId = 1L;

        // When & Then
        mockMvc.perform(delete("/api/incomes/{id}", incomeId))
                .andExpect(status().isNoContent());
    }
}

