package com.azaxxc.effintrakj.effinTrak.Subscription.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String billingCycle;
    private String startDate;
    private String endDate;
    private Boolean isActive;
}

