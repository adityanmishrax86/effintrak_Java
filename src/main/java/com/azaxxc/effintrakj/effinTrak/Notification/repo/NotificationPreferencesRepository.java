package com.azaxxc.effintrakj.effinTrak.Notification.repo;

import com.azaxxc.effintrakj.effinTrak.Notification.model.NotificationPreferences;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationPreferencesRepository extends JpaRepository<NotificationPreferences, Long> {
    Optional<NotificationPreferences> findByUser(User user);
    Optional<NotificationPreferences> findByUserId(Long userId);
}

