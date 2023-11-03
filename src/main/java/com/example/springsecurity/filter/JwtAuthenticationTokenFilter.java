//package com.example.springsecurity.filter;
//import com.example.springsecurity.mapper.RoleResourceMapper;
//import com.example.springsecurity.mapper.UserAuthMapper;
//import com.example.springsecurity.pojo.LoginUser;
//import com.example.springsecurity.util.jwt.JwtUtil;
//import io.jsonwebtoken.Claims;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//import io.jsonwebtoken.SignatureException;
///**
// * JWT过滤器
// */
//@Component
//public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//
////    @Autowired
////    private RedisCache redisCache;
//    @Autowired
//    private UserAuthMapper userAuthMapper;
//    @Autowired
//    private RoleResourceMapper roleResourceMapper;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {
//        //获取token
//        String token = request.getHeader("token");
//        if (!StringUtils.hasText(token)) {
//            //放行
//            filterChain.doFilter(request, response);
//            return;
//        }
//        //解析token获取userid
//        String userid;
//        try {
//            Claims claims = JwtUtil.parseJWT(token);
//            userid = claims.getId();
//        } catch (SignatureException e) {
//            e.printStackTrace();
//            throw new RuntimeException("token非法");
//        }
//        //从redis中获取用户信息
////        String redisKey = "login:" + userid;
////        LoginUser loginUser = redisCache.getCacheObject(redisKey);
////        if(Objects.isNull(loginUser)){
////            throw new RuntimeException("用户未登录");
////        }
//        //从mysql中获取用户信息
//        List<String> permissionKeyList =  roleResourceMapper.listPermsByUserId(Integer.parseInt(userid));
//        LoginUser loginUser = new LoginUser(userAuthMapper.selectById(userid),permissionKeyList);
//
//        //存入SecurityContextHolder
//        //TODO 获取权限信息封装到Authentication中
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        //放行
//        filterChain.doFilter(request, response);
//    }
//}
//


package com.example.springsecurity.filter;
import com.example.springsecurity.mapper.RoleResourceMapper;
import com.example.springsecurity.mapper.UserAuthMapper;
import com.example.springsecurity.pojo.LoginUser;
import com.example.springsecurity.util.jwt.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JWT过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    //    @Autowired
//    private RedisCache redisCache;
    @Autowired
    private UserAuthMapper userAuthMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token获取userid
        String userid;
        Map<String, Object> map = new HashMap<>();
        try {
            boolean verify = JwtUtil.checkToken(token);
            if (verify) {
                // 通过验证
                Claims claims = JwtUtil.parseJWT(token);
                userid = claims.getId();
                //从mysql中获取用户信息
                List<String> permissionKeyList =  roleResourceMapper.listPermsByUserId(Integer.parseInt(userid));
                LoginUser loginUser = new LoginUser(userAuthMapper.selectById(userid),permissionKeyList);

                //user和Authority都存入SecurityContextHolder
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                //放行
                filterChain.doFilter(request, response);
            } else {
                // 未通过验证（但token为空直接被security自动重定向到了登录页面
                map.put("msg", "未通过验证");
                // 将map转为json
                map.put("state", false);
                String json = new ObjectMapper().writeValueAsString(map);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().println(json);  //把报错信息添加到键值对中去
                System.out.println(json);
            }
        //报错信息反馈
        } catch (SignatureException e) {
            e.printStackTrace();
            map.put("msg", "无效签名");
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
            map.put("msg", "不支持的签名");
            // 将map转为json
            map.put("state", false);
            String json = new ObjectMapper().writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(json);  //把报错信息添加到键值对中去
            System.out.println(json);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            map.put("msg", "token过期");
            // 将map转为json
            map.put("state", false);
            String json = new ObjectMapper().writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(json);  //把报错信息添加到键值对中去
            System.out.println(json);
        } catch (MalformedJwtException e) { // IllegalArgumentException
            e.printStackTrace();
            map.put("msg", "不支持的签名格式");
            // 将map转为json
            map.put("state", false);
            String json = new ObjectMapper().writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(json);  //把报错信息添加到键值对中去
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "token无效");
            // 将map转为json
            map.put("state", false);
            String json = new ObjectMapper().writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(json);  //把报错信息添加到键值对中去
            System.out.println(json);
        }
    }
}

