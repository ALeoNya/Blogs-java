package com.example.springsecurity.security;

import com.example.springsecurity.controller.interceptor.LoginInterceptor;
import com.example.springsecurity.security.handler.MyAuthenticationFailureHandler;
import com.example.springsecurity.security.util.MyAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //细粒度控制
public class SecurityConfig {

    //授权（过滤器
    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    MyAuthenticationEntryPoint AuthenticationEntryPoint;
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()  //全域开放(测试开启)

                .antMatchers("/test/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/admin/**").hasRole("admin")  //确实是有用的
                .antMatchers("/boss/**").access("hasAnyRole('boss','admin')")
                .antMatchers("/employe/**").access("hasAnyRole('boss','employe','admin')")
                .anyRequest().authenticated()
                .and()
                .formLogin()  //开启表单验证
                .permitAll();
        http.addFilterBefore((Filter) loginInterceptor,UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();

        ////配置handler处理器(不知道为什么不生效
        http.formLogin()
                .failureHandler(myAuthenticationFailureHandler);
        //配置入口点处理器
//        http.exceptionHandling()
//                .authenticationEntryPoint(AuthenticationEntryPoint);  //认证失败处理器
        //允许跨域
        http.cors(Customizer.withDefaults());

        /* 处理权限不足的情况(前后端分离使用前端的403页面) */
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                String header = req.getHeader("X-Requested-With");
                if("XMLRequest".equals(header)) {
                    //返回json格式
                    res.getWriter().print("403");
                } else {
                    req.getRequestDispatcher("/error403").forward(req,res);
                }
            }
        });
        return http.build();
    }

    /**
     * BCrypt加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private AuthenticationConfiguration configuration;
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        AuthenticationManager auth = configuration.getAuthenticationManager();
        return auth;
    }
}

/**
 * 登陆
 * 成功跳转主页
 * 失败返回json数据给前端，前端选择显示json中的报错信息？or直接使用前端的报错信息？
 */
































