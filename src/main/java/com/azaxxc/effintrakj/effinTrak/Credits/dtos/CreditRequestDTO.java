package com.azaxxc.effintrakj.effinTrak.Credits.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditRequestDTO {
    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Amount is required")
    private Double amount;

    @NotBlank(message = "Due date is required")
    private String dueDate;

    private Long creditorId;

    @NotBlank(message = "Type is required")
    private String type;

    private Double interestRate;

    private String paymentMethod;

    private Boolean paid = false;

    @NotNull(message = "User ID is required")
    private Long userId;
}

