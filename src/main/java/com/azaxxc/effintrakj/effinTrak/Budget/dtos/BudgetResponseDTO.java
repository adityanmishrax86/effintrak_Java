package com.azaxxc.effintrakj.effinTrak.Budget.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetResponseDTO {
    private Long id;
    private Double amount;
    private String startDate;
    private String endDate;
    private String categoryName;
    private Double alertThreshold;
}
