package com.azaxxc.effintrakj.effinTrak.Credits.model;

import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "credits")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false)
    private Double amount;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creditor_id")
    private BankAccount creditor;

    @Column(nullable = false, length = 30)
    private String type;

    private Double interestRate;

    @Column(length = 50)
    private String paymentMethod;

    private Boolean paid = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

