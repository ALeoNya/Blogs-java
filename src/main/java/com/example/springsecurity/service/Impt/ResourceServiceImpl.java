package com.example.springsecurity.service.Impt;

import com.example.springsecurity.mapper.ResourceMapper;
import com.example.springsecurity.pojo.Resource;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.response.Code;
import com.example.springsecurity.response.Msg;
import com.example.springsecurity.util.redis.service.RedisService;
import com.example.springsecurity.service.ResourceService;
import com.example.springsecurity.util.redis.config.InitRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service("ResourceService")
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public Response addResource(Resource resource) {
        int key = resource.getId();
        try {
            resourceMapper.insert(resource);
            redisService.cacheValue(InitRedis.KEY_RESOURCE_LIST, key, resource, 36000);
            if(redisService.getObject(InitRedis.KEY_RESOURCE_LIST, key) == null) {
                return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, "插入数据为空");
            }
            return new Response(Code.SUCCESS, Msg.DEL_SUCCESS_MSG, redisService.cacheValue(InitRedis.KEY_RESOURCE_LIST, key, resource, 360000));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response delResource(Resource resource) {
        int key = resource.getId();
        try {
            redisService.expire(InitRedis.KEY_RESOURCE_LIST, key, 3, TimeUnit.SECONDS);
            resourceMapper.deleteById(key);
            return new Response(Code.SUCCESS, Msg.DEL_SUCCESS_MSG, redisService.containsKey(InitRedis.KEY_RESOURCE_LIST, key));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response selResourceById(Resource resource) {
        int key = resource.getId();
        try {
            if(redisService.containsKey(InitRedis.KEY_RESOURCE_LIST, key)) {
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getObject(InitRedis.KEY_RESOURCE_LIST, key));
            } else {
                Resource get = resourceMapper.selectById(resource.getId());
                //查到null值缓存到redis设置过期时间为6min
                redisService.cacheValue(InitRedis.KEY_RESOURCE_LIST, resource.getId(), get, 360);
                if(get == null) {
                    return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, "你查询的是一个空值");
                }
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getObject(InitRedis.KEY_RESOURCE_LIST, key));
            }

        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response allResource(Resource resource) {
        try {
            return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.allCache(InitRedis.KEY_RESOURCE_LIST));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response updResource(Resource resource) {
        int key = resource.getId();
        try {
            redisService.expire(InitRedis.KEY_RESOURCE_LIST, key, 3, TimeUnit.SECONDS);
            resourceMapper.updateById(resource);
            return new Response(Code.SUCCESS, Msg.UPD_SUCCESS_MSG, redisService.getObject(InitRedis.KEY_RESOURCE_LIST, key));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.UPD_FAIL_MSG, e);
        }
    }
}
