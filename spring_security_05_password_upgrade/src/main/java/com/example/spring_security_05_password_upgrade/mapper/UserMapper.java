package com.example.spring_security_05_password_upgrade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.spring_security_05_password_upgrade.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* @author 神舟
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-05-11 21:27:17
* @Entity com.example.spring_security_04web_verifycode.entity.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(@Param("username") String username);

    int updatePasswordByUsername(@Param("password") String password, @Param("username") String username);
}




