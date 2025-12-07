package com.azaxxc.effintrakj.effinTrak.Credits.controller;

import com.azaxxc.effintrakj.effinTrak.Credits.dtos.CreditRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Credits.dtos.CreditResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Credits.service.CreditService;
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

@WebMvcTest(CreditController.class)
class CreditControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreditService creditService;

    @MockBean
    private UserService userService;

    @MockBean
    private GlobalResponseService globalResponseService;

    @MockBean
    private com.azaxxc.effintrakj.effinTrak.globalcomponents.JWTUtil jwtUtil;

    @Test
    @WithMockUser
    void createCredit_WithValidData_ShouldReturnSuccess() throws Exception {
        // Given
        CreditRequestDTO dto = new CreditRequestDTO();
        dto.setUserId(1L);
        User user = new User();
        user.setId(1L);
        CreditResponseDTO response = new CreditResponseDTO();

        when(userService.findById(1L)).thenReturn(Optional.of(user));
        when(creditService.saveCredit(any(CreditRequestDTO.class), any(User.class))).thenReturn(response);
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(post("/api/credits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getCreditsByUserId_ShouldReturnCredits() throws Exception {
        // Given
        Long userId = 1L;
        CreditResponseDTO credit = new CreditResponseDTO();

        when(creditService.getCreditsByUserId(userId)).thenReturn(List.of(credit));
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(get("/api/credits/user/{userId}", userId))
                .andExpect(status().isOk());
    }
}

