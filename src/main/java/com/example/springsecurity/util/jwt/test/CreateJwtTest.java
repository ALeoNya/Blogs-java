//package com.example.springsecurity.util.jwt.test;
//
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import java.util.Date;
//
//public class CreateJwtTest {
//    public static void main(String[] args) {
//        JwtBuilder builder= Jwts.builder().setId("888").setSubject("小白").setIssuedAt(new
//        Date()).signWith(SignatureAlgorithm.HS256,"qingruan");
//        System.out.println( builder.compact() );
//        //eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE2OTI1NDA2NTV9.IrXWrYYJpBz8xFARfrWO5T3Hjyv8-AkVNYYDulMqG5k
//    }
//}
