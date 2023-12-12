package com.example.springsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class JwtTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    void BCrypt() {
        System.out.println(passwordEncoder);
    }
//    @Autowired
//    private LoginService loginService;

//    @Test
//    void getJWT() {
//        Authority authority = new Authority();
//        authority.setUsername("admin");
//        authority.setPassword("admin");
//        authority.setAuth("admin");
//        System.out.println(loginService.login(authority));
//    }

//    @Test
//    void getAll() {
//        System.out.println(loginService.allUser());
//    }
}
