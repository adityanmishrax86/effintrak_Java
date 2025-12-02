package com.azaxxc.effintrakj.effinTrak.Income.repo;

import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUserId(Long id);

    Page<Income> findByUserIdOrderByDateDesc(Long userId, Pageable pageable);

    Page<Income> findByUserIdAndDateBetweenOrderByDateDesc(Long userId, LocalDate start, LocalDate end,
            Pageable pageable);

    Optional<Income> findByUserIdAndId(Long userId, Long id);

    List<Income> findByUserIdOrderByDateDesc(Long userId);

    List<Income> findByUserIdAndDateBetweenOrderByDateDesc(Long userId, LocalDate start, LocalDate end);
}
