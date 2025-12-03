package com.azaxxc.effintrakj.effinTrak.Income.repo;

import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    Page<Income> findByUserIdAndCategoryIdOrderByDateDesc(Long userId, Long categoryId, Pageable pageable);

    @Query("SELECT i FROM Income i WHERE i.user.id = :userId " +
           "AND (:categoryId IS NULL OR i.category.id = :categoryId) " +
           "AND (:minAmount IS NULL OR i.amount >= :minAmount) " +
           "AND (:maxAmount IS NULL OR i.amount <= :maxAmount) " +
           "AND (:bankAccountId IS NULL OR i.bankAccount.id = :bankAccountId) " +
           "AND (:startDate IS NULL OR i.date >= :startDate) " +
           "AND (:endDate IS NULL OR i.date <= :endDate) " +
           "ORDER BY i.date DESC")
    Page<Income> findIncomesWithFilters(@Param("userId") Long userId,
                                        @Param("categoryId") Long categoryId,
                                        @Param("minAmount") Double minAmount,
                                        @Param("maxAmount") Double maxAmount,
                                        @Param("bankAccountId") Long bankAccountId,
                                        @Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate,
                                        Pageable pageable);

    @Query("SELECT i FROM Income i WHERE i.user.id = :userId " +
           "AND LOWER(i.description) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "ORDER BY i.date DESC")
    List<Income> findByUserIdAndDescriptionContainingIgnoreCase(@Param("userId") Long userId, @Param("search") String search);
}
