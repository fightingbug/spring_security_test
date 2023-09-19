package com.example.spring_security_11_dynamicauthorize;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.spring_security_11_dynamicauthorize.mapper")
public class SpringSecurity11DynamicAuthorizeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity11DynamicAuthorizeApplication.class, args);
    }

}
