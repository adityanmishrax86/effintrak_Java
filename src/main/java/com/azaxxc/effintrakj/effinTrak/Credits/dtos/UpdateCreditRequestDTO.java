package com.azaxxc.effintrakj.effinTrak.Credits.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCreditRequestDTO {
    private String description;
    private Double amount;
    private String dueDate;
    private Long creditorId;
    private String type;
    private Double interestRate;
    private String paymentMethod;
    private Boolean paid;
}

