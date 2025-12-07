package com.azaxxc.effintrakj.effinTrak.Credits.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditResponseDTO {
    private Long id;
    private String description;
    private Double amount;
    private String dueDate;
    private String creditorName;
    private String type;
    private Double interestRate;
    private String paymentMethod;
    private Boolean paid;
}

