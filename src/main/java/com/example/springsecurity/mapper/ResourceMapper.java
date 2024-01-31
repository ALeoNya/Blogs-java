package com.example.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecurity.pojo.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {
    @Update("alter table `kotori`.`k_resource` auto_increment = 1")
    void autoIncrement();
}
