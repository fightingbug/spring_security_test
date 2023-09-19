package com.example.spring_security_05_password_upgrade.service;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.spring_security_05_password_upgrade.entity.User;
import com.example.spring_security_05_password_upgrade.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService, UserDetailsPasswordService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.selectByUsername(username);
        if(ObjectUtils.isEmpty(user)){
            UsernameNotFoundException exception = new UsernameNotFoundException("123");
            throw  exception;
        }
        user.setRoles(roleMapper.getById(user.getId()));
        System.out.println(user);
        return user;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        Integer result = userMapper.updatePasswordByUsername(newPassword,user.getUsername());
        if(result==1){
            ((User)user).setPassword(newPassword);
        }

        return user;
    }
}
