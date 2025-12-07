package com.azaxxc.effintrakj.effinTrak.Notification.model;

import com.azaxxc.effintrakj.effinTrak.users.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 250)
    private String message;

    @Column(nullable = false, length = 50)
    private String type; // "BUDGET_EXCEEDED", "BILL_DUE", "SUBSCRIPTION_RENEWAL", "GOAL_ACHIEVEMENT", "LOW_BALANCE", "UNUSUAL_SPENDING"

    @Column(nullable = false)
    private Boolean isRead = false;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

