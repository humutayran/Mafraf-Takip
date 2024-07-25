package com.umut.Masraf_Takip.service.concrete;

import com.umut.Masraf_Takip.dto.request.UserRequestDto;
import com.umut.Masraf_Takip.dto.response.UserResponseDto;
import com.umut.Masraf_Takip.exception.NotFoundException;
import com.umut.Masraf_Takip.mapper.UserMapper;
import com.umut.Masraf_Takip.model.Role;
import com.umut.Masraf_Takip.model.User;
import com.umut.Masraf_Takip.repository.RoleRepository;
import com.umut.Masraf_Takip.repository.UserRepository;
import com.umut.Masraf_Takip.service.abstraction.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
                .orElseThrow(() -> new NotFoundException(User.class));
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
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class));
    }

    @Override
    public UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto) {
        User userFromDb = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class));

        userFromDb.setEmail(userRequestDto.getEmail());
        userFromDb.setPassword(userRequestDto.getPassword());
        userFromDb.setName(userRequestDto.getName());
        userFromDb.setUsername(userRequestDto.getUsername());

        Set<Role> updatedRoles = new HashSet<>();
        for (Role role : userRequestDto.getRoles()) {
            Role roleFromDb = roleRepository.findByName(role.getName())
                    .orElseThrow(() -> new NotFoundException(Role.class));
            updatedRoles.add(roleFromDb);
        }

        userFromDb.setRoles(updatedRoles);

        userRepository.save(userFromDb);
        return UserMapper.INSTANCE.entityToResponseDto(userFromDb);
    }
}

