package com.azaxxc.effintrakj.effinTrak.Dashboard.controller;

import com.azaxxc.effintrakj.effinTrak.Dashboard.dtos.DashboardResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Dashboard.service.DashboardService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;
    private final GlobalResponseService globalResponseService;

    public DashboardController(DashboardService dashboardService, GlobalResponseService globalResponseService) {
        this.dashboardService = dashboardService;
        this.globalResponseService = globalResponseService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getDashboard(@PathVariable Long userId) {
        DashboardResponseDTO dashboard = dashboardService.getDashboardData(userId);
        return globalResponseService.success(dashboard, "Dashboard data retrieved successfully");
    }
}

