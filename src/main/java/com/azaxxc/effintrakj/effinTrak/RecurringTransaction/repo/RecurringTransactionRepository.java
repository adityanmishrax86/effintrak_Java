package com.azaxxc.effintrakj.effinTrak.RecurringTransaction.repo;

import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.model.RecurringTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RecurringTransactionRepository extends JpaRepository<RecurringTransaction, Long> {
    List<RecurringTransaction> findByUserId(Long userId);
    List<RecurringTransaction> findByUserIdAndIsActiveTrue(Long userId);
    List<RecurringTransaction> findByNextDueDateLessThanEqualAndIsActiveTrue(LocalDate date);
}

