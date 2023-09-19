package com.example.security_custom_rules.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    @GetMapping("/Hello")
    public String hello(){
        System.out.println("Hello");
        return "Hello";
    }

    @GetMapping("/login")
    public String Login(){
        return "login";
    }
}
