package com.example.spring_security_03split.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.example.spring_security_03split.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 神舟
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-05-09 19:24:30
* @Entity com.example.spring_security_03split.entity.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(@Param("username") String username);
}




