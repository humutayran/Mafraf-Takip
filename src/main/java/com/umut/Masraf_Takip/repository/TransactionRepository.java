package com.umut.Masraf_Takip.repository;

import com.umut.Masraf_Takip.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId")
    BigDecimal findTotalAmountByUserId(@Param("userId") Long userId);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId AND t.timestamp BETWEEN :startDate AND :endDate")
    BigDecimal findTotalExpenseByUserAndDateRange(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}


