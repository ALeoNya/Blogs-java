package com.example.springsecurity.security.service.serviceImpl;

import com.example.springsecurity.pojo.*;
import com.example.springsecurity.security.SecurityConfig;
import com.example.springsecurity.security.service.LoginService;
import com.example.springsecurity.service.RoleService;
import com.example.springsecurity.service.UserRoleService;
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
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleService roleService;
    @Override
    public Response login(UserAuth user) {
        try {
            //AuthenticationManager进行用户验证
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            if(Objects.isNull(authenticate)){
                return new Response(416,"任务代号4-1-7","...登录失败");
            }
            //使用userid生成token
            LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
            String userId = loginUser.getUser().getId().toString();
            String jwt = JwtUtil.createJWT(userId);

            //通过userid查询role(从UserRole获取role_id,最后获取role
            UserRole userRole = userRoleService.selUserRoleByUserId(Integer.parseInt(userId));
            //获取auth
            Role role = new Role();
            role.setId(userRole.getRoleId());
            Response res = roleService.selRoleById(role);
            Role role1 = (Role) res.getData();
            //authenticate存入redis
            //redisCache.setCacheObject("login:"+userId,loginUser);

            HashMap<String,String> map = new HashMap<>();
            map.put("token",jwt);
            map.put("status",role1.getRoleName());
            map.put("userId",userId);
            return new Response(417,"欢迎回来,铁御",map);
        } catch (AuthenticationException e) {
            return new Response(416,"任务代号4-1-7","请检查你的账号或密码...登录失败");
        }
    }
}
