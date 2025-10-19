package com.azaxxc.effintrakj.effinTrak.Income.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllIncomeResponse {
    private double totalIncome;
    private List<IncomeResponse> incomes;
}
