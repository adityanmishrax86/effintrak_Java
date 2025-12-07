package com.azaxxc.effintrakj.effinTrak.Transfer.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequestDTO {
    @NotNull(message = "Amount is required")
    private Double amount;

    private String description;

    @NotNull(message = "Transfer date is required")
    private String transferDate;

    @NotNull(message = "From account ID is required")
    private Long fromAccountId;

    @NotNull(message = "To account ID is required")
    private Long toAccountId;

    @NotNull(message = "User ID is required")
    private Long userId;
}

