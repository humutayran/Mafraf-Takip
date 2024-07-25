package com.umut.Masraf_Takip.controller;

import com.umut.Masraf_Takip.dto.request.UserRequestDto;
import com.umut.Masraf_Takip.dto.response.UserResponseDto;
import com.umut.Masraf_Takip.repository.UserRepository;
import com.umut.Masraf_Takip.service.abstraction.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

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

    @PostMapping("/addUser")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        UserResponseDto user = userService.addUser(userRequestDto);
        return new ResponseEntity<>(user, CREATED);
    }


    @GetMapping("/findUser")
    public ResponseEntity<UserResponseDto> getUserByUsername(@RequestParam String username) {
        UserResponseDto user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully.");
    }

    @GetMapping("/findUser/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UserRequestDto userRequestDto
    ) {
        UserResponseDto user = userService.updateUserById(id, userRequestDto);
        return new ResponseEntity<>(user, OK);
    }
}
