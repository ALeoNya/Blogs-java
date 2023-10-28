//package com.example.springsecurity.util.redis;
//
//import com.example.springsecurity.util.response.Code;
//import com.example.springsecurity.util.response.Msg;
//import com.example.springsecurity.pojo.Response;
//import com.example.springsecurity.service.AuthService;
//import com.example.springsecurity.service.RedisService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
///**
// * 用户表暂存redis中
// * 保证一致性
// */
//@RestController
//public class RedisController {
//    //Redis测试类:Postman传值进rides
//    @Resource
//    private RedisTemplate<String,Object> redisTemplate;  //String：键,Object：值
//    @Autowired
//    private AuthService authService;
//    @Autowired
//    private RedisService redisService;
//
//    /**
//     * 调用接口设置过期时间，再更新数据库，完毕再次查询sql再存入缓存
//     */
//    @PostMapping("/updateUser1")
//    public Response updateUser1(@RequestBody Authority authority) {
//        redisService.timeToDie("allUser",2);
//
//        authService.beAdmin(authority);
//
//        List<Authority> allUser = authService.findAllAuth();
//        redisTemplate.opsForValue().set("allUser", allUser);
//
//        return new Response(Code.UPDATE_SUCCESS, Msg.UPDATE_SUCCESS_MSG,redisTemplate.opsForValue().get("allUser"));
//    }
//
//    @PostMapping("/updateUser2")
//    public Response updateUser2(@RequestBody Authority authority) {
//        redisTemplate.expire("allUser",2, TimeUnit.SECONDS);
//
//        authService.beBoss(authority);
//
//        List<Authority> allUser = authService.findAllAuth();
//        if(!redisService.setCache("allUser",allUser)) {
//            return new Response(Code.UPDATE_ERR,Msg.UPDATE_ERR_MSG,null);
//        }
//        return new Response(Code.UPDATE_SUCCESS, Msg.UPDATE_SUCCESS_MSG,redisTemplate.opsForValue().get("allUser"));
//    }
//
//    @PostMapping("/updateUser3")
//    public Response updateUser3(@RequestBody Authority authority) {
//        redisTemplate.expire("allUser",2, TimeUnit.SECONDS);
//
//        authService.beEmploye(authority);
//
//        List<Authority> allUser = authService.findAllAuth();
//        redisTemplate.opsForValue().set("allUser",allUser);
//
//        return new Response(Code.UPDATE_SUCCESS, Msg.UPDATE_SUCCESS_MSG,redisTemplate.opsForValue().get("allUser"));
//    }
//
//    /**
//     * 创建一篇文章
//     */
//
//}