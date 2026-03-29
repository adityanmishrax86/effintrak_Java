package com.azaxxc.effintrakj.effinTrak.Report.controller;

import com.azaxxc.effintrakj.effinTrak.Report.dtos.CategoryTrendDTO;
import com.azaxxc.effintrakj.effinTrak.Report.dtos.ComparisonDTO;
import com.azaxxc.effintrakj.effinTrak.Report.dtos.MonthlyTrendDTO;
import com.azaxxc.effintrakj.effinTrak.Report.dtos.ReportResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Report.service.ReportService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.security.AuthenticatedUserResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;
    private final GlobalResponseService globalResponseService;
    private final AuthenticatedUserResolver authenticatedUserResolver;

    public ReportController(ReportService reportService,
            GlobalResponseService globalResponseService,
            AuthenticatedUserResolver authenticatedUserResolver) {
        this.reportService = reportService;
        this.globalResponseService = globalResponseService;
        this.authenticatedUserResolver = authenticatedUserResolver;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getReport(
            @PathVariable Long userId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        ReportResponseDTO report = reportService.generateReport(effectiveUserId, startDate, endDate);
        return globalResponseService.success(report, "Generated report for user");
    }

    @GetMapping("/user/{userId}/monthly-trend")
    public ResponseEntity<Object> getMonthlyTrend(
            @PathVariable Long userId,
            @RequestParam(required = false) Integer year,
            Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        List<MonthlyTrendDTO> trends = reportService.getMonthlyTrend(effectiveUserId, year);
        return globalResponseService.success(trends, "Monthly trend retrieved successfully");
    }

    @GetMapping("/user/{userId}/category-trend")
    public ResponseEntity<Object> getCategoryTrend(
            @PathVariable Long userId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false, defaultValue = "monthly") String period,
            Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        List<CategoryTrendDTO> trends = reportService.getCategoryTrend(effectiveUserId, categoryId, period);
        return globalResponseService.success(trends, "Category trend retrieved successfully");
    }

    @GetMapping("/user/{userId}/comparison")
    public ResponseEntity<Object> getComparison(
            @PathVariable Long userId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        ComparisonDTO comparison = reportService.getComparisonReport(effectiveUserId, startDate, endDate);
        return globalResponseService.success(comparison, "Comparison report retrieved successfully");
    }
}
