package com.example.springsecurity.security.service.serviceImpl;

import com.example.springsecurity.pojo.LoginUser;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.UserAuth;
import com.example.springsecurity.security.SecurityConfig;
import com.example.springsecurity.security.service.LoginService;
import com.example.springsecurity.util.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
//    @Autowired
//    private RedisCache redisCache;
    @Autowired
    private SecurityConfig securityConfig;

    @Override
    public Response login(UserAuth user) {
        //AuthenticationManager进行用户验证
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
//            if(Objects.isNull(authenticate)){
//                return new Response(416,"任务代号4-1-7","...登录失败");
//            }
            //使用userid生成token
            LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
            String userId = loginUser.getUser().getId().toString();
            String jwt = JwtUtil.createJWT(userId);
            //authenticate存入redis
            //redisCache.setCacheObject("login:"+userId,loginUser);
            //把token响应给前端
            HashMap<String,String> map = new HashMap<>();
            map.put("token",jwt);
            return new Response(417,"欢迎回来,铁御",map);
    } catch (AuthenticationException e) {
        return new Response(416,"任务代号4-1-7","请检查你的账号或密码...登录失败");
    }
    }
}
