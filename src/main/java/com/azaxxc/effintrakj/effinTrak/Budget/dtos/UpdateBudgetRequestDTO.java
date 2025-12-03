package com.azaxxc.effintrakj.effinTrak.Budget.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBudgetRequestDTO {
    private Double amount;
    private String startDate;
    private String endDate;
    private Long categoryId;
    private Double alertThreshold;
}

