package com.example.spring_security_11_dynamicauthorize.service;

import com.example.spring_security_11_dynamicauthorize.entity.Menu;
import com.example.spring_security_11_dynamicauthorize.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> getAll(){
        return menuMapper.getAll();
    }

}
