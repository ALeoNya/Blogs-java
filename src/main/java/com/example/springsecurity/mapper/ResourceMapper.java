package com.example.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecurity.pojo.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {
    @Update("alter table `kotori`.`k_resource` auto_increment = 1")
    void autoIncrement();

    @Select("select * from `kotori`.`k_resource` where resource_name like '%模块'")
    List<Resource> getFamilyName();

    // 自链接
    @Select("SELECT t1.*, t2.* FROM ayra.t_resource t1 JOIN ayra.t_resource t2 ON t1.id = t2.parent_id;")
    List<Resource> testList();

    @Select("select * from `kotori`.`k_resource` where parent_id = ${id}")
    List<Resource> getFamily(int id);
}
