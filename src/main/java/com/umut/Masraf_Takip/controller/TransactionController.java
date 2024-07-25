package com.umut.Masraf_Takip.controller;

import com.umut.Masraf_Takip.dto.request.TransactionRequestDto;
import com.umut.Masraf_Takip.dto.response.TransactionResponseDto;
import com.umut.Masraf_Takip.service.abstraction.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDto> addTransaction(@RequestBody TransactionRequestDto transactionRequestDto) {
        TransactionResponseDto transaction = transactionService.addTransaction(transactionRequestDto);
        return new ResponseEntity<>(transaction, CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> findTransactionById(@PathVariable Long id) {
        TransactionResponseDto transaction = transactionService.getTransactionById(id);
        return new ResponseEntity<>(transaction, OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransactionById(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>("transaction deleted successfully", OK);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDto>> getAllTransactions() {
        List<TransactionResponseDto> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, OK);
    }

    @GetMapping("/userID/{userId}")
    public ResponseEntity<BigDecimal> getTotalAmountByUserId(@PathVariable Long userId) {
        BigDecimal amount = transactionService.getTotalTransactionByUserId(userId);
        return new ResponseEntity<>(amount, OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTransaction(
            @PathVariable Long id,
            @RequestBody TransactionRequestDto transactionRequestDto
    ) {
        String message = transactionService.updateTransaction(id, transactionRequestDto);
        return new ResponseEntity<>(message, OK);
    }
}

