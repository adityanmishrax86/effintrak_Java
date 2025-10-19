package com.azaxxc.effintrakj.effinTrak.accounts.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BankAccountCreateRequestDTO {
    private String bankName;
    private Long userId;

}
