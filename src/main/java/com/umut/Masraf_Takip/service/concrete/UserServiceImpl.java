package com.umut.Masraf_Takip.service.concrete;

import com.umut.Masraf_Takip.dto.request.UserRequestDto;
import com.umut.Masraf_Takip.dto.response.UserResponseDto;
import com.umut.Masraf_Takip.mapper.UserMapper;
import com.umut.Masraf_Takip.model.User;
import com.umut.Masraf_Takip.repository.UserRepository;
import com.umut.Masraf_Takip.service.abstraction.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> usersFromDb = userRepository.findAll();
        return usersFromDb.stream().map(UserMapper.INSTANCE::entityToResponseDto).collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = findUserById(id);
        return UserMapper.INSTANCE.entityToResponseDto(user);
    }

    protected User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public UserResponseDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return UserMapper.INSTANCE.entityToResponseDto(user);
    }

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User user = UserMapper.INSTANCE.requestDtoToEntity(userRequestDto);
        userRepository.save(user);
        return UserMapper.INSTANCE.entityToResponseDto(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}

