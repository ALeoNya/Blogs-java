package com.example.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecurity.pojo.Role;
import com.example.springsecurity.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {
    @Update("alter table `kotori`.`k_tag` auto_increment = 1")
    void autoIncrement();
}
