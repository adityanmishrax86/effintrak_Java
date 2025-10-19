package com.azaxxc.effintrakj.effinTrak.Subscription.model;

import com.azaxxc.effintrakj.effinTrak.users.models.User;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 250)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, length = 20)
    private String billingCycle;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

