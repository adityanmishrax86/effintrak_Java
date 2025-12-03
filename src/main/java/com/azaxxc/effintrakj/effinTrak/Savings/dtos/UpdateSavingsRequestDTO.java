package com.azaxxc.effintrakj.effinTrak.Savings.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSavingsRequestDTO {
    private String name;
    private String description;
    private Double balance;
    private Double targetAmount;
    private String targetDate;
    private String depositFrequency;
}

