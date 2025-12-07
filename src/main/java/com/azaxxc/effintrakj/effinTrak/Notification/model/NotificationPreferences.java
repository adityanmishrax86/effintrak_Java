package com.azaxxc.effintrakj.effinTrak.Notification.model;

import com.azaxxc.effintrakj.effinTrak.users.models.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "notification_preferences")
public class NotificationPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private Boolean budgetAlerts = true;

    @Column(nullable = false)
    private Boolean billReminders = true;

    @Column(nullable = false)
    private Boolean subscriptionRenewals = true;

    @Column(nullable = false)
    private Boolean goalAchievements = true;

    @Column(nullable = false)
    private Boolean lowBalanceAlerts = true;

    @Column(nullable = false)
    private Boolean unusualSpendingAlerts = true;

    @Column(nullable = false)
    private Boolean emailNotifications = false;

    @Column(nullable = false)
    private Boolean pushNotifications = false;
}

