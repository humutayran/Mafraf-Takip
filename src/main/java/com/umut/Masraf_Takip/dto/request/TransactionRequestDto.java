package com.umut.Masraf_Takip.dto.request;

import com.umut.Masraf_Takip.model.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequestDto {
    private Long id;

    private String name;

    private BigDecimal amount;

    private User user;
}
