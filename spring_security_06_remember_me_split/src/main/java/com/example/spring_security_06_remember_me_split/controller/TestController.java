package com.example.spring_security_06_remember_me_split.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/")
    public String Hello(){
        return "ok";
    }

}
