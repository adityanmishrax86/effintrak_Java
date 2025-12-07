package com.azaxxc.effintrakj.effinTrak.util.builders;

import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.users.models.User;

import java.time.LocalDate;

public class IncomeTestDataBuilder {
    private Long id;
    private String description = "Test Income";
    private Double amount = 1000.0;
    private LocalDate date = LocalDate.now();
    private Category category;
    private String source = "Salary";
    private String note = "Test note";
    private User user;
    private BankAccount bankAccount;

    public IncomeTestDataBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public IncomeTestDataBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public IncomeTestDataBuilder withAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public IncomeTestDataBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public IncomeTestDataBuilder withCategory(Category category) {
        this.category = category;
        return this;
    }

    public IncomeTestDataBuilder withSource(String source) {
        this.source = source;
        return this;
    }

    public IncomeTestDataBuilder withNote(String note) {
        this.note = note;
        return this;
    }

    public IncomeTestDataBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public IncomeTestDataBuilder withBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public Income build() {
        Income income = new Income();
        income.setId(id);
        income.setDescription(description);
        income.setAmount(amount);
        income.setDate(date);
        income.setCategory(category);
        income.setSource(source);
        income.setNote(note);
        income.setUser(user);
        income.setBankAccount(bankAccount);
        return income;
    }

    public static IncomeTestDataBuilder anIncome() {
        return new IncomeTestDataBuilder();
    }
}

