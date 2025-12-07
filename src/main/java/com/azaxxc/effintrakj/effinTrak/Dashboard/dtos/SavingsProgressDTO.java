package com.azaxxc.effintrakj.effinTrak.Dashboard.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingsProgressDTO {
    private Long savingsId;
    private String name;
    private Double currentBalance;
    private Double targetAmount;
    private Double progressPercentage;
    private String targetDate;
}

