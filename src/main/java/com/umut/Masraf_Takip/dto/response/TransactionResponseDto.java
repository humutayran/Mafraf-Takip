package com.umut.Masraf_Takip.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionResponseDto {
    private Long id;

    private String name;

    private BigDecimal amount;

    private UserResponseDto user;
}
