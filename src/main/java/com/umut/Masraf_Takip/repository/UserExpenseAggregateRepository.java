package com.umut.Masraf_Takip.repository;

import com.umut.Masraf_Takip.model.UserExpenseAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExpenseAggregateRepository extends JpaRepository<UserExpenseAggregate, Long> {
}
