package com.azaxxc.effintrakj.effinTrak.Report.controller;

import com.azaxxc.effintrakj.effinTrak.Report.dtos.CategoryTrendDTO;
import com.azaxxc.effintrakj.effinTrak.Report.dtos.ComparisonDTO;
import com.azaxxc.effintrakj.effinTrak.Report.dtos.MonthlyTrendDTO;
import com.azaxxc.effintrakj.effinTrak.Report.dtos.ReportResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Report.service.ReportService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;
    private final GlobalResponseService globalResponseService;

    public ReportController(ReportService reportService, GlobalResponseService globalResponseService) {
        this.reportService = reportService;
        this.globalResponseService = globalResponseService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getReport(
            @PathVariable Long userId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        ReportResponseDTO report = reportService.generateReport(userId, startDate, endDate);
        return globalResponseService.success(report, "Generated report for user");
    }

    @GetMapping("/user/{userId}/monthly-trend")
    public ResponseEntity<Object> getMonthlyTrend(
            @PathVariable Long userId,
            @RequestParam(required = false) Integer year) {
        List<MonthlyTrendDTO> trends = reportService.getMonthlyTrend(userId, year);
        return globalResponseService.success(trends, "Monthly trend retrieved successfully");
    }

    @GetMapping("/user/{userId}/category-trend")
    public ResponseEntity<Object> getCategoryTrend(
            @PathVariable Long userId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false, defaultValue = "monthly") String period) {
        List<CategoryTrendDTO> trends = reportService.getCategoryTrend(userId, categoryId, period);
        return globalResponseService.success(trends, "Category trend retrieved successfully");
    }

    @GetMapping("/user/{userId}/comparison")
    public ResponseEntity<Object> getComparison(
            @PathVariable Long userId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        ComparisonDTO comparison = reportService.getComparisonReport(userId, startDate, endDate);
        return globalResponseService.success(comparison, "Comparison report retrieved successfully");
    }
}
