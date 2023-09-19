package com.example.security_userservice.dao;

import java.util.List;

import com.example.security_userservice.entity.Role;
import com.example.security_userservice.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao {

    User loadUserByUsername(String username);
    
    List<Role> getRolesByUid(Integer uid);
}
