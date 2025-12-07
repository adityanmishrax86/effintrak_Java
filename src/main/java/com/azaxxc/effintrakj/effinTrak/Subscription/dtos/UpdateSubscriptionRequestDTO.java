package com.azaxxc.effintrakj.effinTrak.Subscription.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSubscriptionRequestDTO {
    private String name;
    private String description;
    private Double price;
    private String billingCycle;
    private String startDate;
    private String endDate;
    private Boolean isActive;
}

