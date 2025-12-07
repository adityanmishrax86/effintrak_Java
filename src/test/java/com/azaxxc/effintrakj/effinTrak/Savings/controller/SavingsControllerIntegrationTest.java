package com.azaxxc.effintrakj.effinTrak.Savings.controller;

import com.azaxxc.effintrakj.effinTrak.Savings.dtos.SavingsRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Savings.dtos.SavingsResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Savings.service.SavingsService;
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

@WebMvcTest(SavingsController.class)
class SavingsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SavingsService savingsService;

    @MockBean
    private UserService userService;

    @MockBean
    private GlobalResponseService globalResponseService;

    @MockBean
    private com.azaxxc.effintrakj.effinTrak.globalcomponents.JWTUtil jwtUtil;

    @Test
    @WithMockUser
    void createSavings_WithValidData_ShouldReturnSuccess() throws Exception {
        // Given
        SavingsRequestDTO dto = new SavingsRequestDTO();
        dto.setUserId(1L);
        User user = new User();
        user.setId(1L);
        SavingsResponseDTO response = new SavingsResponseDTO();

        when(userService.findById(1L)).thenReturn(Optional.of(user));
        when(savingsService.saveSavings(any(SavingsRequestDTO.class), any(User.class))).thenReturn(response);
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(post("/api/savings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getSavingsByUserId_ShouldReturnSavings() throws Exception {
        // Given
        Long userId = 1L;
        SavingsResponseDTO savings = new SavingsResponseDTO();

        when(savingsService.getSavingsByUserId(userId)).thenReturn(List.of(savings));
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(get("/api/savings/user/{userId}", userId))
                .andExpect(status().isOk());
    }
}

