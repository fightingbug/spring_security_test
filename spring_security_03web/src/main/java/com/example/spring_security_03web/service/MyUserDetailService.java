package com.example.spring_security_03web.service;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.spring_security_03web.entity.User;
import com.example.spring_security_03web.mapper.RoleMapper;
import com.example.spring_security_03web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MyUserDetailService   implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;


    public MyUserDetailService() {

    }

    @Override
    public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException{
        System.out.println("1");

        User user = userMapper.selectByUsername(username);
        System.out.println("2");


        if(ObjectUtils.isEmpty(user)){
            System.out.println("empty");
            UsernameNotFoundException exception = new UsernameNotFoundException("123");
            throw  exception;
        }

        System.out.println("3");
        user.setRoles(roleMapper.getById(user.getId()));
        System.out.println("4");
        System.out.println(user);
        return user;
    }
}
