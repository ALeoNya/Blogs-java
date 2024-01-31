package com.example.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecurity.pojo.Resource;
import com.example.springsecurity.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    @Select("SELECT * FROM k_user_role WHERE user_id = #{userId}")
    UserRole selectByUserId(@Param("userId") int userId);

    @Update("alter table `kotori`.`k_user_role` auto_increment = 1")
    void autoIncrement();
}
