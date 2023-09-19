package com.example.spring_security_10_authorize.controller;


import com.example.spring_security_10_authorize.entity.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hello")
public class AuthorizeMethodController {


    /**
     * preAuthorize 进入前检测权限 支持权限表达式
     * PostAuthorize 返回后检测权限 支持权限表达式
     *
     * preFilter 进入前过滤 支持权限表达式
     * PostFilter返回后过滤 支持权限表达式
     * @return
     */

    @PreAuthorize("hasRole('ADMIN') and authentication.name == 'root'")
    @GetMapping
    public String hello(){return "hello";}

    @GetMapping("/name")
    @PreAuthorize("authentication.name ==#name ")
    public String hello(String name){return "hello"+name;}


    @PostMapping("/users")
    @PreFilter(value="filterObject.id%2!=0",filterTarget = "users")
    public void addUsers(List<User> users){
        System.out.println("users = " + users);
    }


    @GetMapping("/userid ")
    @PostAuthorize("returnObject.id ==1 ")
    public User getUserById(Integer id){return new User(id,"xx");}


    @GetMapping("/lists")
    @PostFilter("filterObject.id%2==0")
    public List<User> getAll(){
        List<User> users = new ArrayList<>();
        for(int i = 0 ;i <10;i++){
            users.add(new User(i,"user"+i));
        }
        return users;
    }

    // 判断角色

    @Secured({"ROLE_USER","ROLE_ADMIN"}) //多选一
    @GetMapping("/secured")
    public User getByUsername(){ return new User(99,"xx");}


    //jsr250

    //@PermitAll @DenyAll @RoleAllowed()类似@Secured()



}
