package com.example.spring_security_04web_verifycode.service;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.spring_security_04web_verifycode.entity.User;
import com.example.spring_security_04web_verifycode.mapper.RoleMapper;
import com.example.spring_security_04web_verifycode.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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
