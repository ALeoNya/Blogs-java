package com.example.springsecurity.util.redis.config;

import com.example.springsecurity.mapper.*;
import com.example.springsecurity.util.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import javax.annotation.PostConstruct;

@Configuration
public class InitRedis {
    @Autowired
    private UserAuthMapper userAuthMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;

    public static final String KEY_USERAUTH_LIST = "DB:k_user_auth:userAuth";
    public static final String KEY_USERROLE_LIST = "DB:k_user_role:userRole";
    public static final String KEY_ROLE_LIST = "DB:k_role:role";
    public static final String KEY_ROLERESOURCE_LIST = "DB:k_role_resource:roleResource";
    public static final String KEY_RESOURCE_LIST = "DB:k_resource:resource";
    public static final String KEY_ARTICLE_LIST = "DB:k_article:article";
    public static final String KEY_ARTICLETAG_LIST = "DB:k_articleTag:articleTag";
    public static final String KEY_CATEGORY_LIST = "DB:k_category:category";
    public static final String KEY_TAG_LIST = "DB:k_tag:tag";
    @PostConstruct  //springboot初始化后自动执行
    public void initRedis() {
        /**
         *初始化 Redis(用户认证授权模块)
         */
//        ArrayList<UserAuth> list = (ArrayList<UserAuth>) userAuthMapper.selectList(null);
//        for(int i = 0; i<list.size(); i++) {
//            int time = 3600000;
//            redisService.cacheValue(list.get(i).getId(), list.get(i), time);
//        }
        userAuthMapper.selectList(null)
                .stream()
                .forEach(userAuth -> redisService.cacheValue(KEY_USERAUTH_LIST, userAuth.getId(), userAuth, 3600000));
        userRoleMapper.selectList(null)
                .stream()
                .forEach(userRole -> redisService.cacheValue(KEY_USERROLE_LIST, userRole.getId(), userRole, 3600000));
        roleMapper.selectList(null)
                .stream()
                .forEach(role-> redisService.cacheValue(KEY_ROLE_LIST, role.getId(), role, 3600000));
        roleResourceMapper.selectList(null)
                .stream()
                .forEach(roleResource -> redisService.cacheValue(KEY_ROLERESOURCE_LIST, roleResource.getId(), roleResource, 3600000));
        resourceMapper.selectList(null)
                .stream()
                .forEach(resource -> redisService.cacheValue(KEY_RESOURCE_LIST, resource.getId(), resource, 3600000));
    }
}
