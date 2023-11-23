package com.example.springsecurity;

import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.pojo.UserAuth;
import com.example.springsecurity.service.RedisService;
import com.example.springsecurity.util.redis.config.InitRedis;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

import static com.example.springsecurity.util.redis.config.InitRedis.KEY_USERAUTH_LIST;

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
        userAuth.setId(2);
        System.out.println(redisService.containsKey(InitRedis.KEY_USERAUTH_LIST, String.valueOf(userAuth.getId())));
//        System.out.println(redisService.containsKey(KEY_USERAUTH_LIST, String.valueOf(userAuth.getId())));
    }

    @Test
    public void Delete() {
        UserAuth userAuth = new UserAuth();
        userAuth.setId(1);
        redisService.removeByKey(InitRedis.KEY_USERAUTH_LIST, String.valueOf(userAuth.getId()));
    }

    @Test
    public void Expire() {
        UserAuth userAuth = new UserAuth();
        userAuth.setId(3);
        redisService.expire(InitRedis.KEY_USERAUTH_LIST, String.valueOf(userAuth.getId()),  5, TimeUnit.SECONDS);
    }
}
