package com.example.springsecurity.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 权限不足处理器
 */
//@Component
//public class AccessDeniedHandleImpl implements AccessDeniedHandler {
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        Response res = new Response(Code.FAILED, Msg.ACCESS_DENIED_MSG, HttpStatus.FORBIDDEN.value());  //403错误
//        Map<String,Object> map = new HashMap<>();
//        map.put("state",res);
//        String json = new ObjectMapper().writeValueAsString(map);
//        response.getWriter().println(json);
//    }
//}
@Component
    public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    @ExceptionHandler(AccessDeniedException.class)
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws AccessDeniedException ,IOException, ServletException {
//        Response res = new Response(Code.FAILED, Msg.ACCESS_DENIED_MSG, HttpStatus.FORBIDDEN.value());  //403错误
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("code","403");
        map.put("msg","权限不足");
        String json = JSON.toJSONString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        System.out.println(json);
    }
}

