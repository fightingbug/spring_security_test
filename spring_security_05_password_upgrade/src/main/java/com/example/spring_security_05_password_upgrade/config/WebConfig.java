package com.example.spring_security_05_password_upgrade.config;


import com.example.spring_security_05_password_upgrade.service.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebConfig {



    @Bean
    public MyUserDetailService myUserDetailService(){
        return new MyUserDetailService();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                request->{
                    request.requestMatchers("/").permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin(form->{

                })
                .csrf().disable();

                return http.build();
    }
}
