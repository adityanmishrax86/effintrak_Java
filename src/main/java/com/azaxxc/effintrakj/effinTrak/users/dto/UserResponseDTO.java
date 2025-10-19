package com.azaxxc.effintrakj.effinTrak.users.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String role;
    private boolean isActive;
}

