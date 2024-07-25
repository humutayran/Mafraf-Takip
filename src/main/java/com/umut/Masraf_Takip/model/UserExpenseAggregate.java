package com.umut.Masraf_Takip.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_EXPENSE_AGGREGATES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserExpenseAggregate extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "PERIOD")
    private String period;

    @Column(name = "TOTAL_EXPENSE", precision = 19, scale = 2)
    private BigDecimal totalExpense = BigDecimal.ZERO;

    @Column(name = "AGGREGATION_DATE")
    private LocalDateTime aggregationDate;
}
