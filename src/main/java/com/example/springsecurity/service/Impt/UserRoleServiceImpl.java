package com.example.springsecurity.service.Impt;

import com.example.springsecurity.mapper.UserRoleMapper;
import com.example.springsecurity.pojo.*;
import com.example.springsecurity.response.Code;
import com.example.springsecurity.response.Msg;
import com.example.springsecurity.service.UserRoleService;
import com.example.springsecurity.util.redis.config.InitRedis;
import com.example.springsecurity.util.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("UserRoleService")
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public Response addUserRole(UserRole userRole) {
        int key = userRole.getId();
        try {
            redisService.expire(InitRedis.KEY_USERROLE_LIST, key, 3, TimeUnit.SECONDS);
            userRoleMapper.deleteById(key);
        } catch(RuntimeException e) {
            return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, e);
        }
        return new Response(Code.SUCCESS, Msg.DEL_SUCCESS_MSG, redisService.getUserRole(userRole));
    }

    @Override
    public Response delUserRole(UserRole userRole) {
        int key = userRole.getId();
        try {
            redisService.expire(InitRedis.KEY_USERROLE_LIST, key, 3, TimeUnit.SECONDS);
            userRoleMapper.deleteById(key);
        } catch(RuntimeException e) {
            return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, e);
        }
        return new Response(Code.SUCCESS, Msg.DEL_SUCCESS_MSG, redisService.getUserRole(userRole));
    }

    @Override
    public Response allUserRole(UserRole userRole) {
        int key = userRole.getId();
        try {
            if(redisService.containsKey(InitRedis.KEY_USERROLE_LIST, key)) {
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getUserRole(userRole));
            } else {
                UserRole get = userRoleMapper.selectById(key);
                redisService.cacheValue(InitRedis.KEY_USERROLE_LIST, key, get, 360);
                if(get == null) {
                    return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, "你查询的是一个空值");
                }
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getUserRole(userRole));
            }

        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response selUserRoleById(UserRole userRole) {
        int key = userRole.getId();
        try {
            if(redisService.containsKey(InitRedis.KEY_USERROLE_LIST, key)) {
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getObject(InitRedis.KEY_USERROLE_LIST, key));
            } else {
                UserRole get = userRoleMapper.selectById(key);
                //查到null值缓存到redis设置过期时间为6min
                if(get == null) {
                    redisService.cacheValue(InitRedis.KEY_USERROLE_LIST, key, get, 360);
                    return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, "你查询的是一个空值");
                }
                redisService.cacheValue(InitRedis.KEY_USERROLE_LIST, key, get, 3600);
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getObject(InitRedis.KEY_USERROLE_LIST, key));
            }
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

    @Override
    public UserRole selUserRoleByUserId(int userid) {
        int key = userid;
        try {
            if(redisService.containsKey(InitRedis.KEY_USERROLE_I_LIST, key)) {
                return redisService.getObject(InitRedis.KEY_USERROLE_I_LIST, key);
            } else {
                UserRole get = userRoleMapper.selectById(key);
                //查到null值缓存到redis设置过期时间为6min
                if(get == null) {
                    redisService.cacheValue(InitRedis.KEY_USERROLE_I_LIST, key, get, 360);
                    return null;
                }
                redisService.cacheValue(InitRedis.KEY_USERROLE_I_LIST, key, get, 3600);
                return redisService.getObject(InitRedis.KEY_USERROLE_I_LIST, key);
            }
        } catch (RuntimeException e) {
            throw e;
//            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response updUserRole(UserRole userRole) {
        int key = userRole.getId();
        try {
            redisService.expire(InitRedis.KEY_USERROLE_LIST, key, 3, TimeUnit.SECONDS);
            userRoleMapper.updateById(userRole);
            return new Response(Code.SUCCESS, Msg.UPD_SUCCESS_MSG, redisService.getUserRole(userRole));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.UPD_FAIL_MSG, e);
        }
    }
}
