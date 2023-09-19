package com.example.spring_security_03web.mapper;

import com.example.spring_security_03web.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 神舟
* @description 针对表【user_role】的数据库操作Mapper
* @createDate 2023-05-05 02:06:16
* @Entity com.example.spring_security_03web.entity.UserRole
*/
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

}




