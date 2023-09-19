package com.example.security_userservice;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@MapperScan(basePackages = "com.example.security_userservice.dao")
public class SecurityUserDetailServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityUserDetailServiceApplication.class,args);
    }

}
