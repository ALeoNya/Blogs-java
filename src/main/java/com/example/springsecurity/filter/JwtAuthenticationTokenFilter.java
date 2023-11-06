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
        if (!StringUtils.hasText(token)) {
            //放行 没有TOKEN就放行到下一个过滤器再判断，这里是判断TOKEN的
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
                //放行 传给过滤器链接中的下一个过滤器如果没有那么传递到请求的资源中去
                filterChain.doFilter(request, response);
            } else {
                // 未通过验证（但token为空直接被security自动重定向到了登录页面
                map.put("msg", "未通过验证");
                // 将map转为json
                MapToJson.mapToJson(map,response);
            }
        //报错信息反馈
        } catch (SignatureException e) {
            e.printStackTrace();
            map.put("msg", "无效签名");
            MapToJson.mapToJson(map,response);
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
            map.put("msg", "不支持的签名");
            map.put("state", false);
            MapToJson.mapToJson(map,response);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            map.put("msg", "token过期");
            map.put("state", false);
            MapToJson.mapToJson(map,response);
        } catch (MalformedJwtException e) { // IllegalArgumentException
            e.printStackTrace();
            map.put("msg", "不支持的签名格式");
            map.put("state", false);
            MapToJson.mapToJson(map,response);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "token无效");
            map.put("state", false);
            MapToJson.mapToJson(map,response);
        }
    }
}

