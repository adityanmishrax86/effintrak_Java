package com.azaxxc.effintrakj.effinTrak.Income.model;

import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "incomes")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false)
    private Double amount;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(length = 50)
    private String source;

    @Column(length = 250)
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_account_id", nullable = false)
    private BankAccount bankAccount;
}

