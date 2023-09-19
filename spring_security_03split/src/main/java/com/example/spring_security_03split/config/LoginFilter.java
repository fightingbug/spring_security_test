package com.example.spring_security_03split.config;

import com.example.spring_security_03split.handler.SuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class LoginFilter extends UsernamePasswordAuthenticationFilter {


    public LoginFilter(AuthenticationManager authenticationManager){
        this.setAuthenticationManager(authenticationManager);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //1.判断是否为POST方式
        if(!request.getMethod().equals("POST")){
            throw  new AuthenticationServiceException("Authentication method not supported:" + request.getMethod());
        }

        //2.判断是否为json格式
        if(request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)){
            try {
                //3.从json提取数据
                Map<String,String> userInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                System.out.println(userInfo);
                String username = userInfo.get(getUsernameParameter());
                String password = userInfo.get(getPasswordParameter());

                UsernamePasswordAuthenticationToken authRequest= new UsernamePasswordAuthenticationToken(username,password);
                setDetails(request,authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return super.attemptAuthentication(request, response);
    }

}
