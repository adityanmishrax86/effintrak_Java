package com.azaxxc.effintrakj.effinTrak.Notification.controller;

import com.azaxxc.effintrakj.effinTrak.Notification.service.NotificationService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final GlobalResponseService globalResponseService;

    public NotificationController(NotificationService notificationService,
            GlobalResponseService globalResponseService) {
        this.notificationService = notificationService;
        this.globalResponseService = globalResponseService;
    }

    @PostMapping("/check-budget/{userId}")
    public ResponseEntity<Object> checkBudget(@PathVariable Long userId) {
        notificationService.checkBudgetExceeded(userId);
        return globalResponseService.success("Budget check triggered successfully");
    }
}
