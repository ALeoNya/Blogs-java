package com.example.springsecurity;

import com.example.springsecurity.util.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
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

    @Test
    void TokenParse() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoiIiwiaXNzIjoic2ciLCJpYXQiOjE3MDI3OTQxNTgsImV4cCI6MTcwMjc5Nzc1OH0.n1Nk4E94wJjTlCAsXl9K6UOECebH4tTT2VXUytnaS5E";
        Claims claims = JwtUtil.parseJWT(token);
        System.out.println(claims);
    }
}
