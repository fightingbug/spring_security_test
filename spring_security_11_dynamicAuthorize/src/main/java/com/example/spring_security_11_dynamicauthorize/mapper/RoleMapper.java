package com.example.spring_security_11_dynamicauthorize.mapper;

import com.example.spring_security_11_dynamicauthorize.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 神舟
* @description 针对表【role】的数据库操作Mapper
* @createDate 2023-05-22 00:27:42
* @Entity com.example.spring_security_11_dynamicauthorize.entity.Role
*/
@Repository
public interface RoleMapper extends BaseMapper<Role> {

}




