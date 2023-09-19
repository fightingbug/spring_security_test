package com.example.spring_security_04web_verifycode.config;


import com.example.spring_security_04web_verifycode.security.filter.KaptchaFilter;
import com.example.spring_security_04web_verifycode.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration

public class SecurityConfig {

    @Lazy
    @Autowired
    private KaptchaFilter kaptchaFilter;

    @Bean
    public MyUserDetailService myUserDetailService(){
        return new MyUserDetailService();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider =  new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailService());
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request)->{
                            request
                                    .requestMatchers("/login","/vc.jpg","/static/**", "/resources/**")
                                    .permitAll()
                                    .anyRequest().authenticated();
                        })
                .addFilterAt(kaptchaFilter,UsernamePasswordAuthenticationFilter.class)
                .formLogin(
                        form->{
                            form.loginPage("/login")
                                    .defaultSuccessUrl("/index");
                        }
                )
                .logout(logout->{
                    logout.permitAll();
                })
                .csrf().disable();
        return http.build();
    }

}
