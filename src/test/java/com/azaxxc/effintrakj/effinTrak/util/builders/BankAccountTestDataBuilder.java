package com.azaxxc.effintrakj.effinTrak.util.builders;

import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.users.models.User;

public class BankAccountTestDataBuilder {
    private Long id;
    private String name = "Test Bank Account";
    private Double balance = 0.0;
    private User user;

    public BankAccountTestDataBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public BankAccountTestDataBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public BankAccountTestDataBuilder withBalance(Double balance) {
        this.balance = balance;
        return this;
    }

    public BankAccountTestDataBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public BankAccount build() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(id);
        bankAccount.setName(name);
        bankAccount.setBalance(balance);
        bankAccount.setUser(user);
        return bankAccount;
    }

    public static BankAccountTestDataBuilder aBankAccount() {
        return new BankAccountTestDataBuilder();
    }
}

