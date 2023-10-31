package com.example.springsecurity;

import com.example.springsecurity.mapper.RoleResourceMapper;
import com.example.springsecurity.pojo.UserAuth;
import com.example.springsecurity.security.SecurityConfig;
import com.example.springsecurity.security.service.LoginService;
import com.example.springsecurity.security.service.serviceImpl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityTests {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    /**
     * 测试loadUserByUsername方法
     */
    @Test
     void demo() {
        userDetailsService.loadUserByUsername("aqua");
    }

    /**
     *
     */
    @Test
    void listPermsByUserId() {
        System.out.println(roleResourceMapper.listPermsByUserId(3));
    }

    @Autowired
    private LoginService loginService;
    @Test
    void setLoginService() {
        UserAuth user =new UserAuth();
        user.setUsername("aqua");
        user.setPassword("aqua");
        loginService.login(user);
    }

    /**
     * BCrypt加密
     */
    @Autowired
    private SecurityConfig securityConfig;
    @Test
    void setBCrypt() {
        System.out.println("密碼通過BCrypt加密后為");
        System.out.println(securityConfig.passwordEncoder().encode("aqua"));
    }
}
