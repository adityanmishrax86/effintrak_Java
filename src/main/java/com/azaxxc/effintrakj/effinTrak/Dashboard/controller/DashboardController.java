package com.azaxxc.effintrakj.effinTrak.Dashboard.controller;

import com.azaxxc.effintrakj.effinTrak.Dashboard.dtos.DashboardResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Dashboard.service.DashboardService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.security.AuthenticatedUserResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;
    private final GlobalResponseService globalResponseService;
    private final AuthenticatedUserResolver authenticatedUserResolver;

    public DashboardController(DashboardService dashboardService,
            GlobalResponseService globalResponseService,
            AuthenticatedUserResolver authenticatedUserResolver) {
        this.dashboardService = dashboardService;
        this.globalResponseService = globalResponseService;
        this.authenticatedUserResolver = authenticatedUserResolver;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getDashboard(@PathVariable Long userId, Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        DashboardResponseDTO dashboard = dashboardService.getDashboardData(effectiveUserId);
        return globalResponseService.success(dashboard, "Dashboard data retrieved successfully");
    }
}
