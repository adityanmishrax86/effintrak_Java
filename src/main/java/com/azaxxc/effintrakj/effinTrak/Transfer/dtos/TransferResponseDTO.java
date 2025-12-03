package com.azaxxc.effintrakj.effinTrak.Transfer.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponseDTO {
    private Long id;
    private Double amount;
    private String description;
    private String transferDate;
    private String fromAccountName;
    private String toAccountName;
}

