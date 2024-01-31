package com.example.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecurity.pojo.UserAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserAuthMapper extends BaseMapper<UserAuth> {
    @Update("alter table `kotori`.`k_user_auth` auto_increment = 1")
    void autoIncrement();
}
