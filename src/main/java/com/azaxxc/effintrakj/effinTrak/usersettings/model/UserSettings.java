package com.azaxxc.effintrakj.effinTrak.usersettings.model;

import com.azaxxc.effintrakj.effinTrak.users.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_settings", uniqueConstraints = @UniqueConstraint(name = "uk_user_settings_user", columnNames = "user_id"))
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 3)
    private String currencyCode = "INR";

    @Column(nullable = false, length = 16)
    private String locale = "en-US";

    @Column(nullable = false, length = 64)
    private String timeZone = "UTC";

    @Column(nullable = false, length = 32)
    private String dateFormat = "dd/MM/yyyy";

    @Column(nullable = false, length = 32)
    private String aiPersona = "balanced";

    @Column(nullable = false)
    private boolean includeProactiveInsights = true;

    @Column(nullable = false)
    private boolean includeCategoryHints = true;

    @Column(nullable = false, length = 10)
    private String weekStartsOn = "MONDAY";

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
