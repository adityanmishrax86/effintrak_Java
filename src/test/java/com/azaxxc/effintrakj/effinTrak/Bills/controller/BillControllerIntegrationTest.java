package com.azaxxc.effintrakj.effinTrak.Bills.controller;

import com.azaxxc.effintrakj.effinTrak.Bills.dtos.BillDTO;
import com.azaxxc.effintrakj.effinTrak.Bills.service.BillService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BillController.class)
class BillControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BillService billService;

    @MockBean
    private GlobalResponseService globalResponseService;

    @MockBean
    private com.azaxxc.effintrakj.effinTrak.globalcomponents.JWTUtil jwtUtil;

    @Test
    @WithMockUser
    void getBillsByUserId_ShouldReturnBills() throws Exception {
        // Given
        Long userId = 1L;
        BillDTO bill = new BillDTO();

        when(billService.getOverdueBills(userId)).thenReturn(List.of(bill));
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(get("/api/bills/user/{userId}/overdue", userId))
                .andExpect(status().isOk());
    }
}

