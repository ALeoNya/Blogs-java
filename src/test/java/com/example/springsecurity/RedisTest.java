package com.example.springsecurity;

import com.example.springsecurity.pojo.ArticleTag;
import com.example.springsecurity.pojo.Resource;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.UserAuth;
import com.example.springsecurity.service.ArticleTagService;
import com.example.springsecurity.util.redis.service.RedisService;
import com.example.springsecurity.util.redis.config.InitRedis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

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
        System.out.println(redisService.containsKey(InitRedis.KEY_USERAUTH_LIST, userAuth.getId()));
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
        redisService.expire(InitRedis.KEY_USERAUTH_LIST,userAuth.getId(),  5, TimeUnit.SECONDS);
    }

    @Test
    public void allCache() {
        String prefix = "DB:k_user_auth";
        String prefix2 = InitRedis.KEY_RESOURCE_LIST;
        System.out.println(redisService.allCache(prefix2));
    }

    @Test
    public void getObject() {
        int k = 1;
        Resource resource = redisService.getObject(InitRedis.KEY_RESOURCE_LIST , k);
        System.out.println(resource);
        if(redisService.getObject(InitRedis.KEY_RESOURCE_LIST , k) == null) {
            System.out.println("Object is null");
        }
    }

    @Autowired
    private ArticleTagService articleTagService;
    @Test
    public void selArticleTagById() {
        ArticleTag articleTag = new ArticleTag();
        articleTag.setId(1);
        articleTagService.selArticleTagById(articleTag);
    }
}
