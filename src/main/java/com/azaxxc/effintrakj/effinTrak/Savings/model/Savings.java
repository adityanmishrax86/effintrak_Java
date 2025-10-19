package com.azaxxc.effintrakj.effinTrak.Savings.model;

import com.azaxxc.effintrakj.effinTrak.users.models.User;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "savings")
public class Savings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 250)
    private String description;

    @Column(nullable = false)
    private Double balance = 0.0;

    private Double targetAmount;

    @Temporal(TemporalType.DATE)
    private Date targetDate;

    @Column(length = 20)
    private String depositFrequency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

