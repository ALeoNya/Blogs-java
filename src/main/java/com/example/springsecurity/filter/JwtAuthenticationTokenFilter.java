package com.example.springsecurity.filter;

import com.example.springsecurity.mapper.RoleResourceMapper;
import com.example.springsecurity.mapper.UserAuthMapper;
import com.example.springsecurity.pojo.LoginUser;
import com.example.springsecurity.util.jwt.JwtUtil;
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

        //为什么登录前会得到token？
        System.out.println(StringUtils.hasText(token));
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userid;
        Map<String, Object> map = new HashMap<>();
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (SignatureException e) {
            e.printStackTrace();
            map.put("msg", "无效签名");
            MapToJson.mapToJson(map,response);
            throw new RuntimeException("无效签名");
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
            map.put("msg", "不支持的签名");
            map.put("state", false);
            MapToJson.mapToJson(map,response);
            throw new RuntimeException("不支持的签名");
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            map.put("msg", "token过期");
            map.put("state", false);
            MapToJson.mapToJson(map,response);
            throw new RuntimeException("token过期");
        } catch (MalformedJwtException e) { // IllegalArgumentException
            e.printStackTrace();
            map.put("msg", "不支持的签名格式");
            map.put("state", false);
            MapToJson.mapToJson(map,response);
            throw new RuntimeException("不支持的签名格式");
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从redis中获取用户信息
        List<String> permissionKeyList =  roleResourceMapper.listPermsByUserId(Integer.parseInt(userid));
        LoginUser loginUser = new LoginUser(userAuthMapper.selectById(userid),permissionKeyList);
        //存入SecurityContextHolder
        //TODO 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}

