package com.example.spring_security_11_dynamicauthorize.mapper;
import java.util.List;

import com.example.spring_security_11_dynamicauthorize.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 神舟
* @description 针对表【menu】的数据库操作Mapper
* @createDate 2023-05-22 00:27:20
* @Entity com.example.spring_security_11_dynamicauthorize.entity.Menu
*/
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> getAll();
}





