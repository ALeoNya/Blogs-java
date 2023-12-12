package com.example.springsecurity.security;

import com.example.springsecurity.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  //细粒度控制
public class SecurityConfig {

    //授权（过滤器
//    @Autowired
//    MyAuthenticationFailureHandler myAuthenticationFailureHandler;
//    @Autowired
//    MyAuthenticationEntryPoint AuthenticationEntryPoint;
    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    AccessDeniedHandlerImpl accessDeniedHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/**").permitAll()  //全域开放(测试开启)

//                .antMatchers("/test/**").permitAll()
//                .antMatchers("/login/**").permitAll()
//                .antMatchers("/admin/**").hasRole("admin")  //确实是有用的
//                .antMatchers("/boss/**").access("hasAnyRole('boss','admin')")
//                .antMatchers("/employe/**").access("hasAnyRole('boss','employe','admin')")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()  //开启表单验证
//                .permitAll();

        //TOKEN Filter
        http.addFilterBefore(jwtAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);

        //异常处理器
        http.exceptionHandling().
//                authenticationEntryPoint(accessDeniedHandle).
                accessDeniedHandler(accessDeniedHandler);
        //关闭CSRF
        http.csrf().disable();

        //允许跨域
        http.cors(Customizer.withDefaults());

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































