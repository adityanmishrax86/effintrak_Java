package com.azaxxc.effintrakj.effinTrak.accounts.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBankAccountRequestDTO {
    private String name;
    private Double balance;
}

