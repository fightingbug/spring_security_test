package com.example.spring_security_03split.config;


import com.example.spring_security_03split.handler.SuccessHandler;
import com.example.spring_security_03split.service.MyUserDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {



    @Bean
    @Autowired
    public LoginFilter loginFilter(AuthenticationManager authenticationManager){
        LoginFilter loginFilter = new LoginFilter(authenticationManager);
        loginFilter.setUsernameParameter("un    ame");
        loginFilter.setPasswordParameter("password");
        loginFilter.setFilterProcessesUrl("/doLogin");
        loginFilter.setAuthenticationSuccessHandler(new SuccessHandler());
        loginFilter.setAuthenticationFailureHandler(((request, response, exception) -> {
            Map<String,Object> result = new HashMap<>();
            result.put("msg","登录失败: "+exception.getMessage());
            result.put("status",500);
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            String s = new ObjectMapper().writeValueAsString(result);
            response.getWriter().println(s);
        }));
        return loginFilter;
    }



    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(MyUserDetailService());
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        return daoAuthenticationProvider;
    }

    @Bean
    public MyUserDetailService MyUserDetailService() {
        MyUserDetailService myUserDetailService = new MyUserDetailService();
        return myUserDetailService;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,LoginFilter loginFilter) throws Exception{
        http.authorizeHttpRequests(request->{
                request.anyRequest().authenticated();
                })
                .formLogin()
                .and()
                .csrf().disable();
        http.addFilterAt(loginFilter,UsernamePasswordAuthenticationFilter.class); //用某个filter替换过滤器链中的过滤器  before放在某个之前 after之后
        return http.build();
    }
}
