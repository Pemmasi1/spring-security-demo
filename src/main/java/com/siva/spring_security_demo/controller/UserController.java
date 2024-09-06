package com.siva.spring_security_demo.controller;

import com.siva.spring_security_demo.dto.UserDto;
import com.siva.spring_security_demo.model.User;
import com.siva.spring_security_demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody UserDto user) {
        userService.saveUser(user);
    }
}
