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



    /**
     * 前端验证接口
     * 解释：从前端返回一个json登录数据之后，将数据返回给工具类login（security的校验步骤都在其中）
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/tologin")
    public Response tologin(@RequestBody UserAuth authority) {
        try {
            // 使用authenticationManager2进行认证，如果失败会抛出AuthenticationException的子类
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authority.getUsername(),authority.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);

            LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
            String userId = loginUser.getUser().getId().toString();
            String jwt = JwtUtil.createJWT(userId);  //ps authority.getAuth()是null
            System.out.println("欢迎回来,铁御");
            return new Response(Code.LOGIN_SUCCESS,Msg.LOGIN_SUCCESS,"欢迎回来,铁御");
        } catch (AuthenticationException e) {
            // 如果认证失败，捕获异常，并返回自定义的信息
            System.out.println("任务代号4-1-7...登录失败");
            return new Response(Code.LOGIN_ERR,Msg.LOGIN_ERR,"任务代号4-1-7...登录失败");
        }
    }
}
