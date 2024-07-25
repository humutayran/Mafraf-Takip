package com.umut.Masraf_Takip.mapper;

import com.umut.Masraf_Takip.dto.request.TransactionRequestDto;
import com.umut.Masraf_Takip.dto.response.TransactionResponseDto;
import com.umut.Masraf_Takip.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionResponseDto entityToResponseDto(Transaction transaction);
    Transaction requestDtoToEntity(TransactionRequestDto transactionRequestDto);
}