package com.azaxxc.effintrakj.effinTrak.Report.controller;

import com.azaxxc.effintrakj.effinTrak.Report.dtos.CategoryTrendDTO;
import com.azaxxc.effintrakj.effinTrak.Report.dtos.ComparisonDTO;
import com.azaxxc.effintrakj.effinTrak.Report.dtos.MonthlyTrendDTO;
import com.azaxxc.effintrakj.effinTrak.Report.dtos.ReportResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Report.service.ReportService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReportController.class)
class ReportControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReportService reportService;

    @MockBean
    private GlobalResponseService globalResponseService;

    @MockBean
    private com.azaxxc.effintrakj.effinTrak.globalcomponents.JWTUtil jwtUtil;

    @Test
    @WithMockUser
    void getReport_ShouldReturnReport() throws Exception {
        // Given
        Long userId = 1L;
        String startDate = "2024-01-01";
        String endDate = "2024-01-31";
        ReportResponseDTO report = new ReportResponseDTO();

        when(reportService.generateReport(anyLong(), anyString(), anyString())).thenReturn(report);
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(get("/api/reports/user/{userId}", userId)
                        .param("startDate", startDate)
                        .param("endDate", endDate))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getMonthlyTrend_ShouldReturnTrends() throws Exception {
        // Given
        Long userId = 1L;
        MonthlyTrendDTO trend = new MonthlyTrendDTO();

        when(reportService.getMonthlyTrend(anyLong(), any())).thenReturn(List.of(trend));
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(get("/api/reports/user/{userId}/monthly-trend", userId))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getCategoryTrend_ShouldReturnTrends() throws Exception {
        // Given
        Long userId = 1L;
        CategoryTrendDTO trend = new CategoryTrendDTO();

        when(reportService.getCategoryTrend(anyLong(), any(), anyString())).thenReturn(List.of(trend));
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(get("/api/reports/user/{userId}/category-trend", userId))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getComparison_ShouldReturnComparison() throws Exception {
        // Given
        Long userId = 1L;
        String startDate = "2024-01-01";
        String endDate = "2024-01-31";
        ComparisonDTO comparison = new ComparisonDTO();

        when(reportService.getComparisonReport(anyLong(), anyString(), anyString())).thenReturn(comparison);
        when(globalResponseService.success(any(), anyString())).thenReturn(org.springframework.http.ResponseEntity.ok().build());

        // When & Then
        mockMvc.perform(get("/api/reports/user/{userId}/comparison", userId)
                        .param("startDate", startDate)
                        .param("endDate", endDate))
                .andExpect(status().isOk());
    }
}

