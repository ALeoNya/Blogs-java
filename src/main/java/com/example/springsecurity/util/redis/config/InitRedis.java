package com.example.springsecurity.util.redis.config;

import com.example.springsecurity.mapper.*;
import com.example.springsecurity.pojo.UserAuth;
import com.example.springsecurity.service.RedisService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class InitRedis {
    @Autowired
    private UserAuthMapper userAuthMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;
    @PostConstruct  //springboot初始化后自动执行
    public void initRedis() {
        // 初始化 Redis(用户认证授权模块)
        ArrayList<UserAuth> list = (ArrayList<UserAuth>) userAuthMapper.selectList(null);
        for(int i = 0; i<list.size(); i++) {
            System.out.println(list.get(i).getId());
            int time = 3600000;
//            redisTemplate.opsForValue().set(list.get(i).getUsername(),list.get(i));
            System.out.println(list.get(i));
            redisService.cacheList(list.get(i).getId(),list.get(i),time);
        }
        UserAuth userAuth = (UserAuth) redisTemplate.opsForValue().get("hiiro");
        System.out.println(userAuth);
        List<UserAuth> userAuth1 = (List<UserAuth>) redisTemplate.opsForValue().get("info:bear:list1");
        System.out.println(userAuth1);
    }

//    @Autowired
//    private RedisTemplate<String, List<UserAuth>> redisTemplateA;
//    @PostConstruct
//    public void getValueFromRedis() {
//        System.out.println(redisTemplateA.opsForValue().get("info:bear:list1"));
//    }
}
