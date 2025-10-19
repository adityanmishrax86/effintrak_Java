package com.azaxxc.effintrakj.effinTrak.Expense.repo;

import com.azaxxc.effintrakj.effinTrak.Expense.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}

