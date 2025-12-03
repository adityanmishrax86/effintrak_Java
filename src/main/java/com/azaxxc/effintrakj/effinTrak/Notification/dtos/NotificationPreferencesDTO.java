package com.azaxxc.effintrakj.effinTrak.Notification.dtos;

import lombok.Data;

@Data
public class NotificationPreferencesDTO {
    private Boolean budgetAlerts;
    private Boolean billReminders;
    private Boolean subscriptionRenewals;
    private Boolean goalAchievements;
    private Boolean lowBalanceAlerts;
    private Boolean unusualSpendingAlerts;
    private Boolean emailNotifications;
    private Boolean pushNotifications;
}

