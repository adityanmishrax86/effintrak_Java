package com.azaxxc.effintrakj.effinTrak.Subscription.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    private Double price;

    @NotBlank(message = "Billing cycle is required")
    private String billingCycle;

    @NotBlank(message = "Start date is required")
    private String startDate;

    private String endDate;

    private Boolean isActive = true;

    @NotNull(message = "User ID is required")
    private Long userId;
}

