package com.example.spring_security_04web_verifycode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@MapperScan("com.example.spring_security_04web_verifycode.mapper")
@EnableWebSecurity
public class SpringSecurity04webVerifycodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity04webVerifycodeApplication.class, args);
    }

}
