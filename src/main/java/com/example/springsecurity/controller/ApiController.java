package com.example.springsecurity.controller;

import com.example.springsecurity.pojo.LoginUser;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.UserAuth;
import com.example.springsecurity.response.Code;
import com.example.springsecurity.response.Msg;
import com.example.springsecurity.security.service.LoginService;
import com.example.springsecurity.security.util.SecurityUtil;
import com.example.springsecurity.util.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ApiController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public Response login(@RequestBody UserAuth user){
        return loginService.login(user);
    }
    @PostMapping("/article/addArticle")
    @PreAuthorize("hasAnyAuthority('/article/addArticle')")  //权限颗粒度
    public Response addArticle() {
        return new Response(Code.SUCCESS, Msg.ADD_SUCCESS_MSG, "addArticle");
    }

    @GetMapping("/article/getArticle")
    @PreAuthorize("hasAnyAuthority('/article/getArticle')")
    public Response getOneArticle() {
        return new Response(Code.SUCCESS, Msg.ADD_SUCCESS_MSG, "getOneArticle");
    }
    @PostMapping("/article/getListArticle")
    @PreAuthorize("hasAnyAuthority('/article/getListArticle')")
    public Response getListArticle() {
        return new Response(Code.SUCCESS, Msg.ADD_SUCCESS_MSG, "getListArticle");
    }
    @PostMapping("/article/delArticle")
    @PreAuthorize("hasAnyAuthority('/article/delArticle')")
    public Response delArticle() {
        return new Response(Code.SUCCESS, Msg.ADD_SUCCESS_MSG, "delArticle");
    }
    @PostMapping("/article/updateArticle")
    @PreAuthorize("hasAnyAuthority('/article/updateArticle')")
    public Response updateArticle() {
        return new Response(Code.SUCCESS, Msg.ADD_SUCCESS_MSG, "updateArticle");
    }
}
