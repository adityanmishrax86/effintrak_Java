package com.azaxxc.effintrakj.effinTrak.Expense.model;

import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false)
    private Double amount;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false, length = 50)
    private String categoryName;

    @Column(length = 50)
    private String paymentMethod;

    @Column(length = 100)
    private String paidTo;

    private Boolean isRecurring = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

