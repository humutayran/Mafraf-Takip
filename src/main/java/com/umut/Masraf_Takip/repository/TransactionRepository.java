package com.umut.Masraf_Takip.repository;

import com.umut.Masraf_Takip.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId")
    BigDecimal findTotalAmountByUserId(@Param("userId") Long userId);
}
