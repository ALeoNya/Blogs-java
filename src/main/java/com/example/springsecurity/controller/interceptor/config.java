//package com.example.springsecurity.controller.interceptor;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//@Configuration
//public class config implements WebMvcConfigurer {
//    @Autowired
//    private LoginInterceptor loginInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor)
//                .excludePathPatterns("/getAllTitle","/tologin","/article/getContentByid","/article/updateArticle","/article/InsertArticle","/InitRedis","/article/deleteArticle","/todo","/minio/uploadFile","/minio/exists"); // 排除登录接口
//    }
//}
