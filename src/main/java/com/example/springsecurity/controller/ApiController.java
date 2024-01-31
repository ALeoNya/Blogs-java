package com.example.springsecurity.controller;

import com.example.springsecurity.filter.MapToJson;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.UserAuth;
import com.example.springsecurity.response.Code;
import com.example.springsecurity.response.Msg;
import com.example.springsecurity.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 一个Controller对应一个Queue
 */
@CrossOrigin
@RestController
public class ApiController {
    @Autowired
    private LoginService loginService;

//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    @PostMapping("/user/login")
    public Response login(@RequestBody UserAuth user){
        return loginService.login(user);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('/hello')")
    public void hello(HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("code","0");
        map.put("msg","HelloWorld");
        MapToJson.mapToJson(map, response);
    }

//    @PostMapping("/helloRabbitMQ")
//    public String RabbitMQ_Pro(@RequestBody UserAuth user){
//        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE_NAME, "topic.demo", JSONObject.toJSONString(user));
//        return "[ RabbitMQ_Pro 生产成功 ]";
//    }

//    @PostMapping("/helloRabbitMQ2")
//    public String RabbitMQ_Pro2(@RequestBody UserAuth user){
//        rabbitTemplate.convertAndSend("my_first_topic_exchange", "topic.demo2", JSONObject.toJSONString(user));
//        return "[ RabbitMQ_Pro2 生产成功 ]";
//    }

//    @PostMapping("/helloRabbitMQ3")
//    public String RabbitMQ_Pro3(@RequestBody UserAuth user){
//        rabbitTemplate.convertAndSend("my_first_topic_exchange", "topic.aaa", JSONObject.toJSONString(user));
//        return "[ RabbitMQ_Pro3 生产成功 ]";
//    }

    @GetMapping("/article/addArticle")
    @PreAuthorize("hasAuthority('/article/addArticle')")  //权限颗粒度
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
    @GetMapping("/article/delArticle")
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
