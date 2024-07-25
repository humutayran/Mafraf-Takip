package com.umut.Masraf_Takip.service.concrete;

import com.umut.Masraf_Takip.dto.request.TransactionRequestDto;
import com.umut.Masraf_Takip.dto.response.TransactionResponseDto;
import com.umut.Masraf_Takip.mapper.TransactionMapper;
import com.umut.Masraf_Takip.model.Transaction;
import com.umut.Masraf_Takip.model.User;
import com.umut.Masraf_Takip.repository.TransactionRepository;
import com.umut.Masraf_Takip.service.abstraction.TransactionService;
import com.umut.Masraf_Takip.service.abstraction.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    TransactionRepository transactionRepository;
    UserService userService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    @Override
    public TransactionResponseDto getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow();
        return TransactionMapper.INSTANCE.entityToResponseDto(transaction);
    }

    @Override
    public TransactionResponseDto addTransaction(TransactionRequestDto transactionRequestDto) {
        User user = userService.getUser(transactionRequestDto.getUser().getId());
        transactionRequestDto.setUser(user);
        Transaction transaction = TransactionMapper.INSTANCE.requestDtoToEntity(transactionRequestDto);
        transactionRepository.save(transaction);
        return TransactionMapper.INSTANCE.entityToResponseDto(transaction);
    }

    @Override
    public List<TransactionResponseDto> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(TransactionMapper.INSTANCE::entityToResponseDto).collect(Collectors.toList());
    }

    @Override
    public String updateTransaction(Long id, TransactionRequestDto transactionRequestDto) {
        Transaction transactionFromDb = transactionRepository.findById(id).orElseThrow();
        User user = userService.getUser(transactionRequestDto.getUser().getId());
        transactionRequestDto.setUser(user);
        transactionFromDb.setName(transactionRequestDto.getName());
        transactionFromDb.setUser(transactionRequestDto.getUser());
        transactionFromDb.setAmount(transactionRequestDto.getAmount());
        transactionRepository.save(transactionFromDb);
        return "record updated successfully";
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalTransactionByUserId(Long id) {
        return transactionRepository.findTotalAmountByUserId(id);
    }
}
