package com.example.springsecurity.security.util;

import com.example.springsecurity.util.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Objects;

public class SecurityUtil {
    /**
     * 编写一个登录接口，接收用户名、密码、验证码参数。根据用户名、密码构建 UsernamePasswordAuthenticationToken 对象，
     * 然后调用官方的方法进行验证，验证用户名、密码是否真实有效；最后将认证对象放入到 Security 的上下文中。
     *     校验流程：前端 => controller => 这里(封装成authenticate) => 再在框架中和UserDetail进行校验 =>最后得出SecurityContextHolder
     */
    public static User login(String username, String password, AuthenticationManager authentcationManager) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);  //构建一个token实例给下面的authenticate()方法使用
        Authentication authenticate = authentcationManager.authenticate(token);  //authentcationManager是一个接口提供authenticate()方法进行数据验证
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        User user = (User) authenticate.getPrincipal();
        return user;
    }
}
