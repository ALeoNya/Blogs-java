package com.example.springsecurity.service.Impt;

import com.example.springsecurity.mapper.UserRoleMapper;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.Role;
import com.example.springsecurity.pojo.RoleResource;
import com.example.springsecurity.pojo.UserRole;
import com.example.springsecurity.response.Code;
import com.example.springsecurity.response.Msg;
import com.example.springsecurity.service.RoleService;
import com.example.springsecurity.service.UserRoleService;
import com.example.springsecurity.util.redis.config.InitRedis;
import com.example.springsecurity.util.redis.service.RedisService;
import org.checkerframework.checker.units.qual.A;
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
        try {
            return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.allCache(InitRedis.KEY_USERROLE_LIST));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
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
