package com.example.springsecurity.service.Impt;

import com.example.springsecurity.mapper.RoleMapper;
import com.example.springsecurity.pojo.Resource;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.Role;
import com.example.springsecurity.response.Code;
import com.example.springsecurity.response.Msg;
import com.example.springsecurity.service.RoleService;
import com.example.springsecurity.util.redis.config.InitRedis;
import com.example.springsecurity.util.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RedisService redisService;
    @Override
    public Response addRole(Role role) {
        int key = role.getId();
        try {
            if(role.getRoleName() == null) {
                return new Response(Code.FAILED, Msg.ADD_FAIL_MSG, "插入数据为空");
            }
            roleMapper.autoIncrement();
            roleMapper.insert(role);
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.ADD_FAIL_MSG, e);
        }
        return new Response(Code.SUCCESS, Msg.ADD_SUCCESS_MSG, role);
    }

    @Override
    public Response delRole(Role role) {
        int key = role.getId();
        try {
            roleMapper.deleteById(key);
            return new Response(Code.SUCCESS, Msg.DEL_SUCCESS_MSG, null);
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response allRole() {
        try {
            return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, roleMapper.selectList(null));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

//    @Override
//    public Response selRoleById(Role role) {
//        int key = role.getId();
//        try {
//            if(redisService.containsKey(InitRedis.KEY_ROLE_LIST, key)) {
//                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getRole(role));
//            } else {
//                Role get = roleMapper.selectById(key);
//                //查到null值缓存到redis设置过期时间为6min
//                if(get == null) {
//                    redisService.cacheValue(InitRedis.KEY_ROLE_LIST, key, get, 360);
//                    return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, "你查询的是一个空值");
//                }
//                redisService.cacheValue(InitRedis.KEY_ROLE_LIST, key, get, 3600);
//                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getRole(role));
//            }
//
//        } catch (RuntimeException e) {
//            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
//        }
//    }

    /**
     * 登录判断权限
     * @param role
     * @return
     */
    @Override
    public Response selRoleById(Role role) {
        int key = role.getId();
        try {
            if(role.getId() == null) {
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, "插入数值不能为空");
            } else {
                Role get = roleMapper.selectById(role.getId());
                //查到null值缓存到redis设置过期时间为6min
                if(get == null) {
                    return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, "你查询的是一个空值");
                }
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, get);
            }

        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response updRole(Role role) {
        int key = role.getId();
        try {
            roleMapper.updateById(role);
            return new Response(Code.SUCCESS, Msg.UPD_SUCCESS_MSG, null);
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.UPD_FAIL_MSG, e);
        }
    }
}
