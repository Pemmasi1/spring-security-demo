package com.siva.spring_security_demo.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping
    public String  hello(HttpServletRequest request) {
        return "Hello From Spring " + request.getSession().getId();
    }
}
