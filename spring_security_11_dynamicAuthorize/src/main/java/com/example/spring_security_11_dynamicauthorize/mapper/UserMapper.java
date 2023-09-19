package com.example.spring_security_11_dynamicauthorize.mapper;
import java.util.List;

import com.example.spring_security_11_dynamicauthorize.entity.Role;
import org.apache.ibatis.annotations.Param;

import com.example.spring_security_11_dynamicauthorize.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 神舟
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-05-22 00:27:46
* @Entity com.example.spring_security_11_dynamicauthorize.entity.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

    User getByUsername(@Param("username") String username);

    List<Role> getUserRoleByUid(Integer uid);

}




