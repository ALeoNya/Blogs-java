package com.example.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecurity.pojo.Resource;
import com.example.springsecurity.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Update("alter table `kotori`.`k_role` auto_increment = 1")
    void autoIncrement();
}
