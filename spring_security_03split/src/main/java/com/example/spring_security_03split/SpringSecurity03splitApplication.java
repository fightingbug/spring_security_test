package com.example.spring_security_03split;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.spring_security_03split.mapper")
public class SpringSecurity03splitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity03splitApplication.class, args);
    }

}
