package com.azaxxc.effintrakj.effinTrak.Income.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateIncomeRequestDTO {
    private String description;
    private Double amount;
    private String date; // Consider using LocalDate for better date handling
    private Long categoryId;
    private String source;
    private String note;
    private Long userId;
}
