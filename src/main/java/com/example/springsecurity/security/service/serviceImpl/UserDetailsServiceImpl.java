package com.example.springsecurity.security.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springsecurity.mapper.RoleResourceMapper;
import com.example.springsecurity.mapper.UserAuthMapper;
import com.example.springsecurity.pojo.LoginUser;
import com.example.springsecurity.pojo.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *  一个接口方法, 用于通过用户名获取用户数据.
 *  返回 UserDetails 对象, 表示用户的核心信息 (用户名, 用户密码, 权限等信息).
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserAuthMapper userAuthMapper;
    @Autowired
    RoleResourceMapper roleResourceMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<UserAuth> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserAuth::getUsername,username);  //LambdaQueryWrapper是查询语句
        UserAuth user = userAuthMapper.selectOne(wrapper);
        System.out.println("根据名字查询到数据库中的用户凭证为: "+ user);
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }
//        System.out.println(user.getId());
        //TODO 根据用户查询权限信息 添加到LoginUser中
        List<String> permissionKeyList = roleResourceMapper.listPermsByUserId(user.getId());
        List<String> list = new ArrayList<>(Arrays.asList("/hello"));
        
        //封装成UserDetails对象返回给AuthtenticationManager
        return new LoginUser(user,list);
    }
}
