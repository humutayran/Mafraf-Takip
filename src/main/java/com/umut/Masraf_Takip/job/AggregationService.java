package com.umut.Masraf_Takip.job;

import com.umut.Masraf_Takip.model.User;
import com.umut.Masraf_Takip.model.UserExpenseAggregate;
import com.umut.Masraf_Takip.repository.TransactionRepository;
import com.umut.Masraf_Takip.repository.UserExpenseAggregateRepository;
import com.umut.Masraf_Takip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AggregationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserExpenseAggregateRepository userExpenseAggregateRepository;

    @Scheduled(cron = "0 0 0 * * ?") // Every day at midnight
    public void aggregateDailyExpenses() {
        aggregateExpenses("DAILY", LocalDateTime.now().minusDays(1), LocalDateTime.now());
    }

    @Scheduled(cron = "0 0 0 * * MON")
    public void aggregateWeeklyExpenses() {
        aggregateExpenses("WEEKLY", LocalDateTime.now().minusWeeks(1), LocalDateTime.now());
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void aggregateMonthlyExpenses() {
        aggregateExpenses("MONTHLY", LocalDateTime.now().minusMonths(1), LocalDateTime.now());
    }

    private void aggregateExpenses(String period, LocalDateTime startDate, LocalDateTime endDate) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            BigDecimal totalExpense = transactionRepository.findTotalExpenseByUserAndDateRange(user.getId(), startDate, endDate);
            UserExpenseAggregate aggregate = UserExpenseAggregate.builder()
                    .user(user)
                    .period(period)
                    .totalExpense(totalExpense != null ? totalExpense : BigDecimal.ZERO)
                    .aggregationDate(LocalDateTime.now())
                    .build();
            userExpenseAggregateRepository.save(aggregate);
        }
    }
}
