package com.azaxxc.effintrakj.effinTrak.Income.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IncomeResponse {
    private String description;
    private double amount;
    private String category;
    private String source;
    private String note;
    private String bankAccount;
    private String date;
    private Long id;
}
