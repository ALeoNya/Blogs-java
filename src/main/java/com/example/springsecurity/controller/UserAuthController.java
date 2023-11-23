package com.example.springsecurity.controller;

import com.example.springsecurity.mapper.UserAuthMapper;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.UserAuth;
import com.example.springsecurity.response.Code;
import com.example.springsecurity.response.Msg;
import com.example.springsecurity.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import static com.example.springsecurity.util.redis.config.InitRedis.KEY_USERAUTH_LIST;

@CrossOrigin
@RestController
public class UserAuthController {
    @Autowired
    private UserAuthMapper userAuthMapper;
    @Autowired
    private RedisService redisService;

    @PostMapping("/addUserAuth")
    public Response addUserAuth(@RequestBody UserAuth userAuth) {
        try {
            userAuthMapper.insert(userAuth);
            return new Response(Code.SUCCESS, Msg.ADD_SUCCESS_MSG, userAuth);
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.ACCESS_DENIED_MSG, e);
        }
    }

    @PostMapping("/getUserAuth")
    public Response getUserAuth(@RequestBody UserAuth userAuth) {
        try {
            //if(redis查询结果){true:返回redis查询到的结果}
            if (redisService.containsKey(KEY_USERAUTH_LIST, String.valueOf(userAuth.getId()))) {
                return new Response(Code.SUCCESS, Msg.ADD_SUCCESS_MSG, redisService.getUserAuth(String.valueOf(userAuth.getId())));
            }
            //为false:查询数据库并且添加到reids中，若数据库也为空则把查询值和空值添加到redis中
            redisService.cacheValue(KEY_USERAUTH_LIST, userAuth.getId(), userAuthMapper.selectById(userAuth.getId()), 3600000);
            return new Response(Code.SUCCESS, Msg.ADD_SUCCESS_MSG, userAuth);
        } catch (Exception e) {
            return new Response(Code.FAILED, Msg.ACCESS_DENIED_MSG, e);
        }
    }

    @PostMapping("/updateUserAuth")
    public Response updateUserAuth(@RequestBody UserAuth userAuth) {
        try {
            //if(redis查询结果){false:返回不存在该用户}
            if (!redisService.containsKey(KEY_USERAUTH_LIST, String.valueOf(userAuth.getId()))) {
                return new Response(Code.FAILED, Msg.ACCESS_DENIED_MSG, "不存在该用户");
            }
            //true:对数据库进行更新操作，对redis数据进行过期处理(延时双删
            redisService.expire(KEY_USERAUTH_LIST, String.valueOf(userAuth.getId()), 3, TimeUnit.SECONDS);
            userAuthMapper.updateById(userAuth);
            return new Response(Code.SUCCESS, Msg.ADD_SUCCESS_MSG, userAuth);
        } catch (Exception e) {
            return new Response(Code.FAILED, Msg.ACCESS_DENIED_MSG, e);
        }
    }

    @PostMapping("/deleteUserAuth")
    public Response deleteUserAuth(@RequestBody UserAuth userAuth) {
        try {
//            if(redis查询结果){false:返回不存在该用户}
            if (!redisService.containsKey(KEY_USERAUTH_LIST, String.valueOf(userAuth.getId()))) {
                return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, "不存在该用户");
            }
            //true:对数据库删除操作，对redis数据进行过期处理
            redisService.expire(KEY_USERAUTH_LIST, String.valueOf(userAuth.getId()), 3, TimeUnit.SECONDS);
            userAuthMapper.deleteById(userAuth);
            return new Response(Code.SUCCESS, Msg.DEL_SUCCESS_MSG, userAuth);
        } catch (Exception e) {
            return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, e);
        }
    }
}