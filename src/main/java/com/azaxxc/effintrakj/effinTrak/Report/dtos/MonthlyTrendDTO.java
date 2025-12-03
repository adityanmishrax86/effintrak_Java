package com.azaxxc.effintrakj.effinTrak.Report.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyTrendDTO {
    private String month;
    private Double income;
    private Double expense;
    private Double balance;
}

