package com.azaxxc.effintrakj.effinTrak.Subscription.service;

import com.azaxxc.effintrakj.effinTrak.Subscription.dtos.SubscriptionRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Subscription.dtos.SubscriptionResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Subscription.dtos.UpdateSubscriptionRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Subscription.model.Subscription;
import com.azaxxc.effintrakj.effinTrak.Subscription.repo.SubscriptionRepository;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.SubscriptionMapper;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;
    private final SubscriptionMapper mapper;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository,
                              UserService userService,
                              SubscriptionMapper mapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.userService = userService;
        this.mapper = mapper;
    }

    public SubscriptionResponseDTO saveSubscription(SubscriptionRequestDTO dto, User user) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (dto.getPrice() == null || dto.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }
        if (dto.getStartDate() == null || dto.getStartDate().isEmpty()) {
            throw new IllegalArgumentException("Start date is required.");
        }

        Subscription subscription = mapper.toSubscription(dto);
        subscription.setUser(user);

        try {
            LocalDate startLocalDate = LocalDate.parse(dto.getStartDate(), formatter);
            subscription.setStartDate(Date.valueOf(startLocalDate));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid start date format. Expected format: yyyy-MM-dd");
        }

        if (dto.getEndDate() != null && !dto.getEndDate().isEmpty()) {
            try {
                LocalDate endLocalDate = LocalDate.parse(dto.getEndDate(), formatter);
                subscription.setEndDate(Date.valueOf(endLocalDate));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid end date format. Expected format: yyyy-MM-dd");
            }
        }

        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return mapper.toSubscriptionResponse(savedSubscription);
    }

    public List<SubscriptionResponseDTO> getSubscriptionsByUserId(Long userId) {
        List<Subscription> subscriptions = subscriptionRepository.findByUserId(userId);
        return subscriptions.stream()
                .map(mapper::toSubscriptionResponse)
                .collect(Collectors.toList());
    }

    public SubscriptionResponseDTO updateSubscription(Long id, UpdateSubscriptionRequestDTO dto) {
        Subscription currentSubscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found with id: " + id));

        if (dto.getName() != null) {
            currentSubscription.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            currentSubscription.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            if (dto.getPrice() <= 0) {
                throw new IllegalArgumentException("Price must be greater than zero.");
            }
            currentSubscription.setPrice(dto.getPrice());
        }
        if (dto.getBillingCycle() != null) {
            currentSubscription.setBillingCycle(dto.getBillingCycle());
        }
        if (dto.getStartDate() != null && !dto.getStartDate().isEmpty()) {
            try {
                LocalDate startLocalDate = LocalDate.parse(dto.getStartDate(), formatter);
                currentSubscription.setStartDate(Date.valueOf(startLocalDate));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid start date format. Expected format: yyyy-MM-dd");
            }
        }
        if (dto.getEndDate() != null && !dto.getEndDate().isEmpty()) {
            try {
                LocalDate endLocalDate = LocalDate.parse(dto.getEndDate(), formatter);
                currentSubscription.setEndDate(Date.valueOf(endLocalDate));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid end date format. Expected format: yyyy-MM-dd");
            }
        } else if (dto.getEndDate() != null && dto.getEndDate().isEmpty()) {
            currentSubscription.setEndDate(null);
        }
        if (dto.getIsActive() != null) {
            currentSubscription.setIsActive(dto.getIsActive());
        }

        try {
            Subscription updatedSubscription = subscriptionRepository.save(currentSubscription);
            return mapper.toSubscriptionResponse(updatedSubscription);
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't update the subscription.");
        }
    }

    public Optional<Subscription> getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

    public SubscriptionResponseDTO getSubscriptionResponseById(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found with id: " + id));
        return mapper.toSubscriptionResponse(subscription);
    }

    public List<SubscriptionResponseDTO> getUpcomingRenewals(Long userId, Integer days) {
        LocalDate now = LocalDate.now();
        LocalDate futureDate = now.plusDays(days != null ? days : 30);
        
        List<Subscription> subscriptions = subscriptionRepository.findByUserId(userId);
        return subscriptions.stream()
                .filter(sub -> sub.getIsActive() && sub.getEndDate() != null)
                .filter(sub -> {
                    LocalDate endDate = sub.getEndDate().toInstant()
                            .atZone(java.time.ZoneId.systemDefault())
                            .toLocalDate();
                    return endDate.isAfter(now) && endDate.isBefore(futureDate) || endDate.isEqual(futureDate);
                })
                .map(mapper::toSubscriptionResponse)
                .collect(Collectors.toList());
    }

    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }
}

