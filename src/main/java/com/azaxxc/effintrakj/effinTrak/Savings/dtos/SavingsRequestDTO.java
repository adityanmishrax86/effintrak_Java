package com.azaxxc.effintrakj.effinTrak.Savings.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavingsRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    private Double balance = 0.0;

    private Double targetAmount;

    private String targetDate;

    private String depositFrequency;

    @NotNull(message = "User ID is required")
    private Long userId;
}

