package com.umut.Masraf_Takip.controller;

import com.umut.Masraf_Takip.dto.response.UserResponseDto;
import com.umut.Masraf_Takip.service.abstraction.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController  {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto user = userService.addUser(userRequestDto);
        return new ResponseEntity<>(user, CREATED);
    }
}
