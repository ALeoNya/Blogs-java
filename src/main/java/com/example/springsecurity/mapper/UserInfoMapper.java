package com.example.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springsecurity.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    @Update("alter table `kotori`.`k_user_info` auto_increment = 1")
    void autoIncrement();
}
