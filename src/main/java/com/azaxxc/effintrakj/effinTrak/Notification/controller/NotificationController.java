package com.azaxxc.effintrakj.effinTrak.Notification.controller;

import com.azaxxc.effintrakj.effinTrak.Notification.dtos.NotificationPreferencesDTO;
import com.azaxxc.effintrakj.effinTrak.Notification.dtos.NotificationResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Notification.service.NotificationService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getNotifications(@PathVariable Long userId) {
        List<NotificationResponseDTO> notifications = notificationService.getNotificationsByUserId(userId);
        return globalResponseService.success(notifications, "Fetched notifications for user");
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<Object> getUnreadNotifications(@PathVariable Long userId) {
        List<NotificationResponseDTO> notifications = notificationService.getUnreadNotificationsByUserId(userId);
        return globalResponseService.success(notifications, "Fetched unread notifications for user");
    }

    @GetMapping("/user/{userId}/unread-count")
    public ResponseEntity<Object> getUnreadCount(@PathVariable Long userId) {
        Long count = notificationService.getUnreadCount(userId);
        return globalResponseService.success(Map.of("count", count), "Unread notification count");
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Object> markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return globalResponseService.success("Notification marked as read");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return globalResponseService.success("Notification deleted successfully");
    }

    @GetMapping("/user/{userId}/preferences")
    public ResponseEntity<Object> getPreferences(@PathVariable Long userId) {
        NotificationPreferencesDTO preferences = notificationService.getPreferences(userId);
        return globalResponseService.success(preferences, "Fetched notification preferences");
    }

    @PutMapping("/user/{userId}/preferences")
    public ResponseEntity<Object> updatePreferences(@PathVariable Long userId,
                                                     @RequestBody NotificationPreferencesDTO dto) {
        NotificationPreferencesDTO updated = notificationService.updatePreferences(userId, dto);
        return globalResponseService.success(updated, "Notification preferences updated successfully");
    }
}
