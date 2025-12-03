package com.azaxxc.effintrakj.effinTrak.Subscription.repo;

import com.azaxxc.effintrakj.effinTrak.Subscription.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(Long userId);
}

