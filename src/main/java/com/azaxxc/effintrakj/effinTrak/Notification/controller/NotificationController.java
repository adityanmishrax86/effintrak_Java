package com.azaxxc.effintrakj.effinTrak.Notification.controller;

import com.azaxxc.effintrakj.effinTrak.Notification.dtos.NotificationPreferencesDTO;
import com.azaxxc.effintrakj.effinTrak.Notification.dtos.NotificationResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Notification.service.NotificationService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.security.AuthenticatedUserResolver;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final GlobalResponseService globalResponseService;
    private final AuthenticatedUserResolver authenticatedUserResolver;

    public NotificationController(NotificationService notificationService,
            GlobalResponseService globalResponseService,
            AuthenticatedUserResolver authenticatedUserResolver) {
        this.notificationService = notificationService;
        this.globalResponseService = globalResponseService;
        this.authenticatedUserResolver = authenticatedUserResolver;
    }

    @PostMapping("/check-budget/{userId}")
    public ResponseEntity<Object> checkBudget(@PathVariable Long userId, Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        notificationService.checkBudgetExceeded(effectiveUserId);
        return globalResponseService.success("Budget check triggered successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getNotifications(@PathVariable Long userId, Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        List<NotificationResponseDTO> notifications = notificationService.getNotificationsByUserId(effectiveUserId);
        return globalResponseService.success(notifications, "Fetched notifications for user");
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<Object> getUnreadNotifications(@PathVariable Long userId, Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        List<NotificationResponseDTO> notifications = notificationService.getUnreadNotificationsByUserId(effectiveUserId);
        return globalResponseService.success(notifications, "Fetched unread notifications for user");
    }

    @GetMapping("/user/{userId}/unread-count")
    public ResponseEntity<Object> getUnreadCount(@PathVariable Long userId, Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        Long count = notificationService.getUnreadCount(effectiveUserId);
        return globalResponseService.success(Map.of("count", count), "Unread notification count");
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Object> markAsRead(@PathVariable Long id, Authentication authentication) {
        Long authenticatedUserId = authenticatedUserResolver.resolveUserId(authentication);
        if (authenticatedUserId == null) {
            notificationService.markAsRead(id);
        } else {
            notificationService.markAsReadForUser(authenticatedUserId, id);
        }
        return globalResponseService.success("Notification marked as read");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteNotification(@PathVariable Long id, Authentication authentication) {
        Long authenticatedUserId = authenticatedUserResolver.resolveUserId(authentication);
        if (authenticatedUserId == null) {
            notificationService.deleteNotification(id);
        } else {
            notificationService.deleteNotificationForUser(authenticatedUserId, id);
        }
        return globalResponseService.success("Notification deleted successfully");
    }

    @GetMapping("/user/{userId}/preferences")
    public ResponseEntity<Object> getPreferences(@PathVariable Long userId, Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        NotificationPreferencesDTO preferences = notificationService.getPreferences(effectiveUserId);
        return globalResponseService.success(preferences, "Fetched notification preferences");
    }

    @PutMapping("/user/{userId}/preferences")
    public ResponseEntity<Object> updatePreferences(@PathVariable Long userId,
                                                     @RequestBody NotificationPreferencesDTO dto,
                                                     Authentication authentication) {
        Long effectiveUserId = authenticatedUserResolver.resolveRequestedUserId(authentication, userId);
        NotificationPreferencesDTO updated = notificationService.updatePreferences(effectiveUserId, dto);
        return globalResponseService.success(updated, "Notification preferences updated successfully");
    }
}
