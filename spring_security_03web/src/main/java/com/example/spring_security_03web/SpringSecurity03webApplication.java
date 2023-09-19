package com.example.spring_security_03web;




import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan(value = "com.example.spring_security_03web.mapper")
public class SpringSecurity03webApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity03webApplication.class, args);
    }

}
