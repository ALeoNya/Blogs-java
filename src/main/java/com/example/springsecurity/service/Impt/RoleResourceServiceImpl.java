package com.example.springsecurity.service.Impt;

import com.example.springsecurity.mapper.ResourceMapper;
import com.example.springsecurity.mapper.RoleResourceMapper;
import com.example.springsecurity.pojo.Resource;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.RoleResource;
import com.example.springsecurity.response.Code;
import com.example.springsecurity.response.Msg;
import com.example.springsecurity.util.redis.config.InitRedis;
import com.example.springsecurity.util.redis.service.RedisService;
import com.example.springsecurity.service.RoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("RoleResourceService")
public class RoleResourceServiceImpl implements RoleResourceService {
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private RedisService redisService;
    /**
     * 为某个角色添加一个权限
     * @param roleResource
     * @return
     */
    @Override
    public Response addRoleResource(RoleResource roleResource) {
        int key = roleResource.getId();
        try {
            roleResourceMapper.insert(roleResource);
            redisService.cacheValue(InitRedis.KEY_ROLERESOURCE_LIST, key, roleResource, 36000);
            if(redisService.getRoleResource(roleResource) == null) {
                return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, "插入数据为空");
            }
            return new Response(Code.SUCCESS, Msg.DEL_SUCCESS_MSG, redisService.cacheValue(InitRedis.KEY_ROLERESOURCE_LIST, key, roleResource, 36000));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, e);
        }
    }

    /**
     * 删除角色与权限的关系
     * @param roleResource
     * @return
     */
    @Override
    public Response delRoleResource(RoleResource roleResource) {
        int key = roleResource.getId();
        try {
            redisService.expire(InitRedis.KEY_ROLERESOURCE_LIST, key, 3, TimeUnit.SECONDS);
            roleResourceMapper.deleteById(key);
        } catch(RuntimeException e) {
            return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, e);
        }
        return new Response(Code.SUCCESS, Msg.DEL_SUCCESS_MSG, redisService.getRoleResource(roleResource));
    }

    @Override
    public Response selRoleResourceById(RoleResource roleResource) {
        int key = roleResource.getId();
        try {
            if(redisService.containsKey(InitRedis.KEY_ROLERESOURCE_LIST, key)) {
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getRoleResource(roleResource));
            } else {
                RoleResource get = roleResourceMapper.selectById(key);
                //查到null值缓存到redis设置过期时间为6min
                if(get == null) {
                    redisService.cacheValue(InitRedis.KEY_ROLERESOURCE_LIST, key, get, 360);
                    return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, "你查询的是一个空值");
                }
                redisService.cacheValue(InitRedis.KEY_ROLERESOURCE_LIST, key, get, 3600);
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getRoleResource(roleResource));
            }

        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response allRoleResource(RoleResource roleResource) {
        try {
            return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.allCache(InitRedis.KEY_ROLERESOURCE_LIST));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response updRoleResource(RoleResource roleResource) {
        int key = roleResource.getId();
        try {
            redisService.expire(InitRedis.KEY_ROLERESOURCE_LIST, key, 3, TimeUnit.SECONDS);
            roleResourceMapper.updateById(roleResource);
            return new Response(Code.SUCCESS, Msg.UPD_SUCCESS_MSG, redisService.getRoleResource(roleResource));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.UPD_FAIL_MSG, e);
        }
    }
}
