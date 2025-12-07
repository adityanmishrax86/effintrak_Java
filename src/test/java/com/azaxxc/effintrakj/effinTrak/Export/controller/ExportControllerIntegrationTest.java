package com.azaxxc.effintrakj.effinTrak.Export.controller;

import com.azaxxc.effintrakj.effinTrak.Export.service.ExportService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExportController.class)
class ExportControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ExportService exportService;

    @MockBean
    private GlobalResponseService globalResponseService;

    @MockBean
    private com.azaxxc.effintrakj.effinTrak.globalcomponents.JWTUtil jwtUtil;

    @Test
    @WithMockUser
    void exportTransactions_ShouldReturnExport() throws Exception {
        // Given
        Long userId = 1L;
        String format = "csv";
        byte[] exportData = "test,data".getBytes();

        try {
            when(exportService.exportTransactionsToCSV(anyLong(), anyString(), anyString()))
                    .thenReturn(exportData);

            // When & Then
            mockMvc.perform(get("/api/export/user/{userId}/transactions", userId)
                            .param("format", format)
                            .param("startDate", "2024-01-01")
                            .param("endDate", "2024-01-31"))
                    .andExpect(status().isOk());
        } catch (java.io.IOException e) {
            // Handle IOException if needed
        }
    }
}

