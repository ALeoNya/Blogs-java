package com.example.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecurity.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    @Update("alter table `kotori`.`k_category` auto_increment = 1")
    void autoIncrement();
}
