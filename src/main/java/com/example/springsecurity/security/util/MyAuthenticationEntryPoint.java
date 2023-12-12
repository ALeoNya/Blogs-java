//package com.example.springsecurity.security.util;
//
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
//
//@Component
////public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
////    @Override
////    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
////        //处理响应josn
////        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  //这个方法是用来开启认证方案的，如果没有设置响应状态为401（未授权），那么Spring Security会认为认证成功，不会执行后续的认证流程
////        response.setContentType("application/json;charset=UTF-8");
////        response.getWriter().write(JSONObject.toJSONString(new LoginResponse(100,"任务代号4-1-7...登录失败","null","null")));
////        System.out.println("任务代号4-1-7...登录失败");
////    }
////}
//
//public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
//    @Override
//    public void commence (HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
////        response.setContentType("application/json;charset=UTF-8");
////        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////        response.getWriter().write(JSONObject.toJSONString(new LoginResponse(100,"任务代号4-1-7...登录失败","null","null")));
//
//        response.setContentType("application/json;charset=UTF-8");
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        PrintWriter out = response.getWriter();
//
//        // 创建一个json数据对象，你可以根据你的需求修改其中的内容
//        Map<String, Object> map = new HashMap<>();
//        map.put("auth", "null");
//        map.put("code", 100);
//        map.put("msg", "任务代号4-1-7...登录失败");
//        map.put("token", "null");
//        out.write(JSONObject.toJSONString(map));
//        out.flush();
//        out.close();
//    }
//}
