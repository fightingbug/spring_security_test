package com.example.spring_security_03web.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.spring_security_03web.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 神舟
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-05-05 02:05:42
* @Entity com.example.spring_security_03web.entity.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {
    User selectByUsername(@Param("username") String username);


}




