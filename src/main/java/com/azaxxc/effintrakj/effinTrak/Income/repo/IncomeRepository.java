package com.azaxxc.effintrakj.effinTrak.Income.repo;

import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUserId(Long id);

    Page<Income> findByUserIdAndDateBetweenOrderByDateDesc(Long userId, Date startDate, Date endDate, Pageable pageable);
}
