package com.azaxxc.effintrakj.effinTrak.Bills.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private Long id;
    private String type; // "CREDIT" or "SUBSCRIPTION"
    private String description;
    private String name;
    private Double amount;
    private String dueDate;
    private Integer daysUntilDue;
    private Boolean isOverdue;
}

