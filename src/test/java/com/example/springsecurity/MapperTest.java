package com.example.springsecurity;

import com.example.springsecurity.mapper.UserRoleMapper;
import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.service.UserRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MapperTest {
    @Autowired
    UserRoleMapper userRoleMapper;
    @Test
    public void userRoleMapperTest() {
        System.out.println(userRoleMapper.selectById(3));
        System.out.println(userRoleMapper.selectByUserId(3));
    }
}
