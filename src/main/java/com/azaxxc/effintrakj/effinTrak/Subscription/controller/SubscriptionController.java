package com.azaxxc.effintrakj.effinTrak.Subscription.controller;

import com.azaxxc.effintrakj.effinTrak.Subscription.dtos.SubscriptionRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Subscription.dtos.SubscriptionResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Subscription.dtos.UpdateSubscriptionRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Subscription.service.SubscriptionService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final UserService userService;
    private final GlobalResponseService globalResponseService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService,
                                 UserService userService,
                                 GlobalResponseService globalResponseService) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
        this.globalResponseService = globalResponseService;
    }

    @PostMapping
    public ResponseEntity<Object> createSubscription(@Valid @RequestBody SubscriptionRequestDTO dto) {
        Long userId = dto.getUserId();
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        SubscriptionResponseDTO savedSubscription = subscriptionService.saveSubscription(dto, user);
        return globalResponseService.success(savedSubscription, "Subscription created successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getSubscriptionsByUserId(@PathVariable Long userId) {
        List<SubscriptionResponseDTO> subscriptions = subscriptionService.getSubscriptionsByUserId(userId);
        return globalResponseService.success(subscriptions, "Fetched subscriptions for user");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSubscriptionById(@PathVariable Long id) {
        try {
            SubscriptionResponseDTO subscription = subscriptionService.getSubscriptionResponseById(id);
            return globalResponseService.success(subscription, "Fetched subscription");
        } catch (RuntimeException e) {
            return globalResponseService.error("Subscription not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSubscription(@PathVariable Long id, @RequestBody UpdateSubscriptionRequestDTO dto) {
        SubscriptionResponseDTO updatedSubscription = subscriptionService.updateSubscription(id, dto);
        return globalResponseService.success(updatedSubscription, "Subscription updated successfully");
    }

    @GetMapping("/user/{userId}/upcoming-renewals")
    public ResponseEntity<Object> getUpcomingRenewals(
            @PathVariable Long userId,
            @RequestParam(required = false, defaultValue = "30") Integer days) {
        List<SubscriptionResponseDTO> subscriptions = subscriptionService.getUpcomingRenewals(userId, days);
        return globalResponseService.success(subscriptions, "Fetched upcoming subscription renewals");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return globalResponseService.success("Subscription deleted successfully");
    }
}

