package com.umut.Masraf_Takip.mapper;

import com.umut.Masraf_Takip.dto.request.UserRequestDto;
import com.umut.Masraf_Takip.dto.response.UserResponseDto;
import com.umut.Masraf_Takip.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDto entityToResponseDto(User user);
    User requestDtoToEntity(UserRequestDto userRequestDto);
    User responseDtoToEntity(UserResponseDto userResponseDto);
}
