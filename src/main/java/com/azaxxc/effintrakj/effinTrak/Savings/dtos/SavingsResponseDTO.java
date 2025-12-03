package com.azaxxc.effintrakj.effinTrak.Savings.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SavingsResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Double balance;
    private Double targetAmount;
    private String targetDate;
    private String depositFrequency;
}

