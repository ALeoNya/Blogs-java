package com.example.springsecurity.util.redis.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springsecurity.mapper.*;
import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.pojo.Category;
import com.example.springsecurity.service.*;
import com.example.springsecurity.util.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
public class InitRedis {
    @Autowired
    private UserAuthMapper userAuthMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisService redisService;

    public static final String KEY_USERINFO_LIST = "DB:k_user_Info:userInfo";
    public static final String KEY_USERAUTH_LIST = "DB:k_user_auth:userAuth";

    public static final String KEY_USERROLE_LIST = "DB:k_user_role:userRole";
    public static final String KEY_USERROLE_I_LIST = "DB:k_user_role_I:userRole";


    public static final String KEY_ROLE_LIST = "DB:k_role:role";
    public static final String KEY_ROLERESOURCE_LIST = "DB:k_role_resource:roleResource";
    public static final String KEY_RESOURCE_LIST = "DB:k_resource:resource";
    public static final String KEY_ARTICLE_LIST = "DB:k_article:article:not_delete";
    public static final String KEY_ARTICLE_LIST_DELETE = "DB:k_article:recycle:is_delete";
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
//            int time = 36000000;
//            redisService.cacheValue(list.get(i).getId(), list.get(i), time);
//        }
        userInfoMapper.selectList(null)
                .stream()
                .forEach(userInfo -> redisService.cacheValue(KEY_USERINFO_LIST, userInfo.getId(), userInfo, 36000));
        userAuthMapper.selectList(null)
                .stream()
                .forEach(userAuth -> redisService.cacheValue(KEY_USERAUTH_LIST, userAuth.getId(), userAuth, 36000));
        userRoleMapper.selectList(null)
                .stream()
                .forEach(userRole -> redisService.cacheValue(KEY_USERROLE_LIST, userRole.getId(), userRole, 36000));
        roleMapper.selectList(null)
                .stream()
                .forEach(role-> redisService.cacheValue(KEY_ROLE_LIST, role.getId(), role, 36000));
        roleResourceMapper.selectList(null)
                .stream()
                .forEach(roleResource -> redisService.cacheValue(KEY_ROLERESOURCE_LIST, roleResource.getId(), roleResource, 36000));
        resourceMapper.selectList(null)
                .stream()
                .forEach(resource -> redisService.cacheValue(KEY_RESOURCE_LIST, resource.getId(), resource, 36000));
        categoryMapper.selectList(null)
                .stream()
                .forEach(category -> redisService.cacheValue(KEY_CATEGORY_LIST, category.getId(), category, 36000));
        tagMapper.selectList(null)
                .stream()
                .forEach(tag -> redisService.cacheValue(KEY_TAG_LIST, tag.getId(), tag, 36000));
        articleTagMapper.selectList(null)
                .stream()
                .forEach(articleTag -> redisService.cacheValue(KEY_ARTICLETAG_LIST, articleTag.getId(), articleTag, 36000));

        // 文章列表
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete",0);
        articleMapper.selectList(wrapper)
                .stream()
                .forEach(articles -> redisService.cacheValue(KEY_ARTICLE_LIST, articles.getId(), articles, 36000));
//                .forEach(article -> redisService.cacheZsetValue(KEY_ARTICLE_LIST, article.getId(), article, 36000));


        // 文章回收站列表
        QueryWrapper<Article> articleDeleteWrapper = new QueryWrapper<>();
        articleDeleteWrapper.eq("is_delete",1);
        articleMapper.selectList(articleDeleteWrapper)
                .stream()
                .forEach(article -> redisService.cacheValue(KEY_ARTICLE_LIST_DELETE, article.getId(), article, 36000));
//                .forEach(article -> redisService.cacheZsetValue(KEY_ARTICLE_LIST_DELETE, article.getId(), article, 36000));
    }
}
