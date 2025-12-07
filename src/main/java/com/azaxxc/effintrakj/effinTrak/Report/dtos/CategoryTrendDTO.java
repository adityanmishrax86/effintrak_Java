package com.azaxxc.effintrakj.effinTrak.Report.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryTrendDTO {
    private String categoryName;
    private Map<String, Double> monthlyAmounts; // Key: month (YYYY-MM), Value: amount
}

