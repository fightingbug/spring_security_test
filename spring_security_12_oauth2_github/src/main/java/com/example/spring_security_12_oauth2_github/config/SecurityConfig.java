package com.example.spring_security_12_oauth2_github.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            http.authorizeHttpRequests(request->{
                request.anyRequest().authenticated();
            }).oauth2Login(oauth2->{

            }).csrf(csrf->{
                csrf.disable();
            });
        return http.build();
    }
}
