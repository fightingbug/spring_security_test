package com.example.security_userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.security_userservice.security.MyUserDetailService;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    private MyUserDetailService myUserDetailService;

    @Autowired
    public WebSecurityConfig(MyUserDetailService sevice){
        this.myUserDetailService = sevice;
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{


        http
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/", "/index").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(
                        withDefaults()
                )

                ;

        return http.build();
    }



}
