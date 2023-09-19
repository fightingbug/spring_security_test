package com.example.spring_security_03web.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.spring_security_03web.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 神舟
* @description 针对表【role】的数据库操作Mapper
* @createDate 2023-05-05 02:03:44
* @Entity spring_security_03web.entity.Role
*/
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> getById(@Param("id") Integer id);
}




