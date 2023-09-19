package com.example.security_userservice.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class IndexController {
    
    @GetMapping("/index")
    public String Index(){
        System.out.println("Index");
        return "Index";
    }
}
