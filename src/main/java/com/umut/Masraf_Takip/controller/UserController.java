package com.umut.Masraf_Takip.controller;

import com.umut.Masraf_Takip.service.abstraction.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController  {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


}
