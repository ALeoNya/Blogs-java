//package com.example.springsecurity.util.jwt.test;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//
//public class ParseJwtTest {
//    public static void main(String[] args) {
//        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwMDEiLCJzdWIiOiJhZG1pbiIsImlhdCI6MTY5MzM4NzI1NCwiZXhwIjoxNjkzMzkwODU0fQ.P-a9SDXDyYlxATr8xPEjLQtMc9Pdbx2eWJBOHL3luAY";
//        Claims claims = Jwts.parser().setSigningKey("qingruan").parseClaimsJws(token).getBody();
//        System.out.println("id:"+claims.getId());
//        System.out.println("subject:"+claims.getSubject());
//        System.out.println("IssuedAt:"+claims.getIssuedAt());
//    }
//}
