package com.example.spring_security_05_password_upgrade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.spring_security_05_password_upgrade.mapper")
public class SpringSecurity05PasswordUpgradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity05PasswordUpgradeApplication.class, args);
	}

}
