package com.azaxxc.effintrakj.effinTrak.Expense.repo;

import com.azaxxc.effintrakj.effinTrak.Expense.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Page<Expense> findByUserIdOrderByDateDesc(Long userId, Pageable pageable);

    Page<Expense> findByUserIdAndDateBetweenOrderByDateDesc(Long userId, LocalDate start, LocalDate end,
            Pageable pageable);

    Optional<Expense> findByUserIdAndId(Long userId, Long id);

    List<Expense> findByUserIdOrderByDateDesc(Long userId);

    List<Expense> findByUserIdAndDateBetweenOrderByDateDesc(Long userId, LocalDate start, LocalDate end);

    Page<Expense> findByUserIdAndCategoryIdOrderByDateDesc(Long userId, Long categoryId, Pageable pageable);

    @Query("SELECT e FROM Expense e WHERE e.user.id = :userId " +
           "AND (:categoryId IS NULL OR e.category.id = :categoryId) " +
           "AND (:minAmount IS NULL OR e.amount >= :minAmount) " +
           "AND (:maxAmount IS NULL OR e.amount <= :maxAmount) " +
           "AND (:paymentMethod IS NULL OR e.paymentMethod = :paymentMethod) " +
           "AND (:bankAccountId IS NULL OR e.bankAccount.id = :bankAccountId) " +
           "AND (:startDate IS NULL OR e.date >= :startDate) " +
           "AND (:endDate IS NULL OR e.date <= :endDate) " +
           "ORDER BY e.date DESC")
    Page<Expense> findExpensesWithFilters(@Param("userId") Long userId,
                                          @Param("categoryId") Long categoryId,
                                          @Param("minAmount") Double minAmount,
                                          @Param("maxAmount") Double maxAmount,
                                          @Param("paymentMethod") String paymentMethod,
                                          @Param("bankAccountId") Long bankAccountId,
                                          @Param("startDate") LocalDate startDate,
                                          @Param("endDate") LocalDate endDate,
                                          Pageable pageable);

    @Query("SELECT e FROM Expense e WHERE e.user.id = :userId " +
           "AND LOWER(e.description) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "ORDER BY e.date DESC")
    List<Expense> findByUserIdAndDescriptionContainingIgnoreCase(@Param("userId") Long userId, @Param("search") String search);
}
