package com.example.spring_security_03split.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.example.spring_security_03split.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 神舟
* @description 针对表【role】的数据库操作Mapper
* @createDate 2023-05-09 19:25:21
* @Entity com.example.spring_security_03split.entity.Role
*/
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getById(@Param("id") Integer id);
}




