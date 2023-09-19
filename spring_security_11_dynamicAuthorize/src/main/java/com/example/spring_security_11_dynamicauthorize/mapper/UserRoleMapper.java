package com.example.spring_security_11_dynamicauthorize.mapper;

import com.example.spring_security_11_dynamicauthorize.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 神舟
* @description 针对表【user_role】的数据库操作Mapper
* @createDate 2023-05-22 00:27:49
* @Entity com.example.spring_security_11_dynamicauthorize.entity.UserRole
*/
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

}




