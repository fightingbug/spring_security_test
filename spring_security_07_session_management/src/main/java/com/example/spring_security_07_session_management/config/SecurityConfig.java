package com.example.spring_security_07_session_management.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.session.FindByIndexNameSessionRepository;

import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private FindByIndexNameSessionRepository findByIndexNameSessionRepository;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request->{
            request.anyRequest().authenticated();
        })
                .formLogin()
                .and()
                .sessionManagement(
                        httpSecuritySessionManagementConfigurer -> {
                            httpSecuritySessionManagementConfigurer.maximumSessions(1)
                                    //.expiredUrl("/login");  会话过期传统web处理
                                    .maxSessionsPreventsLogin(true) // 设置需要等当前设备下线才能登录 不允许挤下线
                                    .sessionRegistry(springSessionBackedSessionRegistry())
                                    .expiredSessionStrategy( event -> {  //前后端分离处理
                                        HttpServletResponse response = event.getResponse();
                                        response.setContentType("application/json;charset=UTF-8");
                                        Map<String,Object> result = new HashMap<>();
                                        result.put("status",500);
                                        result.put("msg","当前会话已失效");
                                        String s = new ObjectMapper().writeValueAsString(result);
                                        response.getWriter().println(s);
                                        response.flushBuffer();
                                    });
                        }
                )
                .csrf().disable();

        return http.build();
    }


    @Bean
    public SpringSessionBackedSessionRegistry springSessionBackedSessionRegistry(){
        return new SpringSessionBackedSessionRegistry(findByIndexNameSessionRepository);
    }


/*    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }*/

}
