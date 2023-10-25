package com.example.springsecurity.service.Impt;

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
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }
        //TODO 根据用户查询权限信息 添加到LoginUser中
        List<String> permissionKeyList =  roleResourceMapper.listPermsByUserId(user.getId());
//        List<String> list = new ArrayList<>(Arrays.asList("admin"));  //测试先:写死

        //封装成UserDetails对象返回
        return new LoginUser(user,permissionKeyList);
    }
}
