package com.azaxxc.effintrakj.effinTrak.Report.controller;

import com.azaxxc.effintrakj.effinTrak.Report.dtos.ReportResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Report.service.ReportService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
