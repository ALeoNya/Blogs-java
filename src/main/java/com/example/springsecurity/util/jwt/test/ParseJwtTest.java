package com.example.springsecurity.util.jwt.test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import static com.example.springsecurity.util.jwt.JwtUtil.parseJWT;

public class ParseJwtTest {
    public static void main(String[] args) {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoiIiwiaXNzIjoic2ciLCJpYXQiOjE2OTg3MTM2NjYsImV4cCI6MTY5ODcxNzI2Nn0.dqvaJdJa8s86Vbudl7HD38_ygoCFdI2f7GQ7R-UIQzY";
//        Claims claims = Jwts.parser().setSigningKey("qingruan").parseClaimsJws(token).getBody();
//        System.out.println("id:"+claims.getId());
//        System.out.println("subject:"+claims.getSubject());
//        System.out.println("IssuedAt:"+claims.getIssuedAt());
        Claims claims = parseJWT(token);
        System.out.println(claims.getId());
    }
}
