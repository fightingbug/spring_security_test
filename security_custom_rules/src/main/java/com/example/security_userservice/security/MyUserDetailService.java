package com.example.security_userservice.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.example.security_userservice.dao.UserDao;
import com.example.security_userservice.entity.Role;
import com.example.security_userservice.entity.User;

@Component
public class MyUserDetailService implements UserDetailsService{


    private final UserDao userDao;


    @Autowired
    public MyUserDetailService(UserDao userDao){
        this.userDao = userDao;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user =  userDao.loadUserByUsername(username);
        if(ObjectUtils.isEmpty(user)) throw new UsernameNotFoundException("用户名不正确");
        List<Role> roles =userDao.getRolesByUid(user.getId());
        user.setRoles(roles);
        return user;
    }
        
    
}
