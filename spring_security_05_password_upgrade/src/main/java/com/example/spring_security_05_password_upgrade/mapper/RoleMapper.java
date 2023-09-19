package com.example.spring_security_05_password_upgrade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.spring_security_05_password_upgrade.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 神舟
* @description 针对表【role】的数据库操作Mapper
* @createDate 2023-05-11 21:26:59
* @Entity com.example.spring_security_04web_verifycode.entity.Role
*/
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getById(@Param("id") Integer id);

}




