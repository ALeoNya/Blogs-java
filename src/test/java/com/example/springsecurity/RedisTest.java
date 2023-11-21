package com.example.springsecurity;

import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.pojo.UserAuth;
import com.example.springsecurity.service.RedisService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisService redisService;
    @Test
    public void LombokBuilder() {
        UserAuth userAuth = (UserAuth) redisTemplate.opsForValue().get("info:bear:list1");
        System.out.println(userAuth);
    }
    @Test
    public void test() {
        UserAuth userAuth = new UserAuth();
        userAuth.setId(1);
        System.out.println(redisService.containsUserAuthKey(String.valueOf(userAuth.getId())));
    }

}
