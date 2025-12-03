package com.azaxxc.effintrakj.effinTrak.Credits.repo;

import com.azaxxc.effintrakj.effinTrak.Credits.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByUserId(Long userId);
    List<Credit> findByUserIdAndDueDateBetween(Long userId, Date startDate, Date endDate);
}

