package com.example.springsecurity;

import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.pojo.UserAuth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void LombokBuilder() {
        UserAuth userAuth = (UserAuth) redisTemplate.opsForValue().get("info:bear:list1");
        System.out.println(userAuth);
    }
}
