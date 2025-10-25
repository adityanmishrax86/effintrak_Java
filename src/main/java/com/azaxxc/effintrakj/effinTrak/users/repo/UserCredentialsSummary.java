package com.azaxxc.effintrakj.effinTrak.users.repo;

public interface UserCredentialsSummary {
    String getPassword();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getRole();
    String getPhoneNumber();
    Boolean getIsActive();
}
