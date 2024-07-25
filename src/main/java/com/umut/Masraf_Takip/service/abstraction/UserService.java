package com.umut.Masraf_Takip.service.abstraction;

import com.umut.Masraf_Takip.dto.request.UserRequestDto;
import com.umut.Masraf_Takip.dto.response.UserResponseDto;
import com.umut.Masraf_Takip.model.User;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long id);
    UserResponseDto getUserByUsername(String username);
    UserResponseDto addUser(UserRequestDto userRequestDto);
    void deleteUserById(Long id);
    User getUser(Long id);

    UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto);
}
