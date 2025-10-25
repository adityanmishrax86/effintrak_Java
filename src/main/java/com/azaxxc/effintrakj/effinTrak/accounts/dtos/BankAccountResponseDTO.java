package com.azaxxc.effintrakj.effinTrak.accounts.dtos;

import com.azaxxc.effintrakj.effinTrak.users.dto.UserResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankAccountResponseDTO {
    private Long id;
    private String name;
    private Double balance;
}
