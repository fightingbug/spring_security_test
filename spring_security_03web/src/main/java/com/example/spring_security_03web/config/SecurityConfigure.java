package com.example.spring_security_03web.config;

import com.example.spring_security_03web.service.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigure {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
            http.authorizeHttpRequests( request->{
                request.requestMatchers("/login.html","/error.html").permitAll()
                        .anyRequest().authenticated();})
                        .formLogin( login->{
                            login.loginPage("/login.html")
                                .loginProcessingUrl("/doLogin")
                                .passwordParameter("password")
                                .usernameParameter("uname")
                                .defaultSuccessUrl("/index.html",true)
                                    .failureUrl("/error.html");

                         })
                        .logout((logout) -> logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/login.html")
                                .permitAll())
                        .csrf().disable();

        return http.build();
    }




    @Bean
    public MyUserDetailService myUserDetailService(){
        MyUserDetailService myUserDetailService = new MyUserDetailService();
        return myUserDetailService;
    }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailService());
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }

}
