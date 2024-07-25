package com.umut.Masraf_Takip.service.abstraction;


import com.umut.Masraf_Takip.dto.request.TransactionRequestDto;
import com.umut.Masraf_Takip.dto.response.TransactionResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    TransactionResponseDto getTransactionById(Long id);
    TransactionResponseDto addTransaction(TransactionRequestDto transactionRequestDto);
    List<TransactionResponseDto> getAllTransactions();
    String updateTransaction(Long id, TransactionRequestDto transactionRequestDto);
    void deleteTransaction(Long id);
    BigDecimal getTotalTransactionByUserId(Long id);
}
