package com.azaxxc.effintrakj.effinTrak.Savings.repo;

import com.azaxxc.effintrakj.effinTrak.Savings.model.Savings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavingsRepository extends JpaRepository<Savings, Long> {
    List<Savings> findByUserId(Long userId);
}

