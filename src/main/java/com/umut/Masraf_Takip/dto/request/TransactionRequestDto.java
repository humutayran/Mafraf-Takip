package com.umut.Masraf_Takip.dto.request;

import com.umut.Masraf_Takip.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequestDto {

    @NotEmpty(message = "Başlık boş bırakılamaz")
    private String name;

    @NotNull(message = "Miktar boş bırakılamaz")
    private BigDecimal amount;

    @NotNull
    private User user;
}
