package com.azaxxc.effintrakj.effinTrak.util.builders;

import com.azaxxc.effintrakj.effinTrak.Expense.model.Expense;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.users.models.User;

import java.time.LocalDate;

public class ExpenseTestDataBuilder {
    private Long id;
    private String description = "Test Expense";
    private Double amount = 50.0;
    private LocalDate date = LocalDate.now();
    private Category category;
    private String paymentMethod = "Credit Card";
    private String paidTo = "Test Merchant";
    private Boolean isRecurring = false;
    private User user;
    private BankAccount bankAccount;

    public ExpenseTestDataBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ExpenseTestDataBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ExpenseTestDataBuilder withAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public ExpenseTestDataBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public ExpenseTestDataBuilder withCategory(Category category) {
        this.category = category;
        return this;
    }

    public ExpenseTestDataBuilder withPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public ExpenseTestDataBuilder withPaidTo(String paidTo) {
        this.paidTo = paidTo;
        return this;
    }

    public ExpenseTestDataBuilder withIsRecurring(Boolean isRecurring) {
        this.isRecurring = isRecurring;
        return this;
    }

    public ExpenseTestDataBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public ExpenseTestDataBuilder withBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public Expense build() {
        Expense expense = new Expense();
        expense.setId(id);
        expense.setDescription(description);
        expense.setAmount(amount);
        expense.setDate(date);
        expense.setCategory(category);
        expense.setPaymentMethod(paymentMethod);
        expense.setPaidTo(paidTo);
        expense.setIsRecurring(isRecurring);
        expense.setUser(user);
        expense.setBankAccount(bankAccount);
        return expense;
    }

    public static ExpenseTestDataBuilder anExpense() {
        return new ExpenseTestDataBuilder();
    }
}

