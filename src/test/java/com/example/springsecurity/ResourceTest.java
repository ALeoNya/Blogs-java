package com.example.springsecurity;

import com.example.springsecurity.pojo.Resource;
import com.example.springsecurity.util.redis.service.RedisService;
import com.example.springsecurity.service.ResourceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ResourceTest {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RedisService redisService;
    @Test
    public void selResourceById() {
        Resource resource = new Resource();
        resource.setId(2050);
        System.out.println(resourceService.selResourceById(resource));
//        System.out.println(redisService.containsKey(InitRedis.KEY_RESOURCE_LIST, resource.getId()));
//        System.out.println(redisService.getResource(resource));
    }
}
