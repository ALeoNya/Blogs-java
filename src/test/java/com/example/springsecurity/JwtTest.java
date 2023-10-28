//package com.example.springsecurity;
//
//import com.example.springsecurity.pojo.Authority;
//import com.example.springsecurity.service.LoginService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class JwtTest {
//    @Autowired
//    private LoginService loginService;
//
//    @Test
//    void getJWT() {
//        Authority authority = new Authority();
//        authority.setUsername("admin");
//        authority.setPassword("admin");
//        authority.setAuth("admin");
//        System.out.println(loginService.login(authority));
//    }
//
//    @Test
//    void getAll() {
//        System.out.println(loginService.allUser());
//    }
//}
