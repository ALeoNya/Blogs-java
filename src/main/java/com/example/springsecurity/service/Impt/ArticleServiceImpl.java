package com.example.springsecurity.service.Impt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springsecurity.mapper.ArticleMapper;
import com.example.springsecurity.mapper.ArticleTagMapper;
import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.pojo.ArticleTag;
import com.example.springsecurity.service.ArticleService;
import com.example.springsecurity.service.ArticleTagService;
import com.example.springsecurity.util.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.example.springsecurity.util.redis.config.InitRedis.*;


@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加文章（OK
     * @param article
     * @return
     */
    @Override
    public boolean addArticle(Article article) {
        try {
            System.out.println(article);
            // 对数据库操作
            articleMapper.insert(article);
            // 对Redis操作
            redisService.cacheValue(KEY_ARTICLE_LIST, article.getId(), article, 3600);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 假删除文章（OK
     * @param article
     * @return
     */
    @Override
    public boolean fakeDelArticle(Article article) {
        try {
//            long start = System.currentTimeMillis();

            // 过期redis的article
            redisService.expire(KEY_ARTICLE_LIST,article.getId(),4,TimeUnit.SECONDS);
            // 改变is_delete字段值
            articleMapper.fakeDelArticle(article);
            // 查询数据库中修改后的数据并且添加到redis的recycle
            redisService.cacheValue(KEY_ARTICLE_LIST_DELETE, article.getId(), articleMapper.selectById(article.getId()), 3600);

//            long end = System.currentTimeMillis();
//            long duration = end - start;
//            System.out.println("接口运行时长为"+duration);
        } catch (RuntimeException ignored) {
            return false;
        }
        return true;
    }

    /**
     * 真删除文章（OK
     * @param article
     * @return
     */
    @Override
    public boolean delArticle(Article article) {
        try {
            // 将recycle中的数据过期
            redisService.expire(KEY_ARTICLE_LIST_DELETE, article.getId(), 3, TimeUnit.SECONDS);
            // 删除k_Article对应的记录
            articleMapper.deleteById(article.getId());
            // 同时删除mysql，redis中article_tag表(根据article_id查询删除
            redisService.expire(KEY_ARTICLETAG_LIST, article.getId(), 3, TimeUnit.SECONDS);
            QueryWrapper<ArticleTag> qw_ArticleTag = new QueryWrapper<>();
            qw_ArticleTag.eq("article_id",article.getId());
            articleTagMapper.delete(qw_ArticleTag);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 查询单篇文章（OK
     * @param article
     * @return
     */
    @Override
    public Article selArticleById(Article article) {
        try {
            if(redisService.containsKey(KEY_ARTICLE_LIST, article.getId())) {
                return redisService.getArticle(article);
            }
        } catch (Exception e) {
            return null;
        }
        redisService.cacheValue(KEY_ARTICLE_LIST, article.getId(), articleMapper.selectById(article.getId()), 360000);
        return redisService.getArticle(article);
    }

    /**
     * 回收站列表
     */
    @Override
    public Map<String, Object> allDelArticle() {
        Map<String, Object> redisData = new HashMap<>();
        Set<String> keys = redisTemplate.keys("DB:k_article:recycle:is_delete*");
        if(keys == null) {
            QueryWrapper<Article> wrapper = new QueryWrapper<>();
            wrapper.eq("is_delete",1);
            articleMapper.selectList(wrapper)
                    .stream()
                    .forEach(articles -> redisService.cacheValue(KEY_ARTICLE_LIST, articles.getId(), articles, 3600));
        }
        for (String key : keys) {
            redisData.put(key, redisTemplate.opsForValue().get(key));
        }
        return redisData;
    }

    /**
     * 所有文章 列表（OK
     * @return
     */
    @Override
    public Map<String, Object> allArticle() {
        Map<String, Object> redisData = new HashMap<>();
        Set<String> keys = redisTemplate.keys("DB:k_article:article:not_delete*");
        if(keys == null) {
            QueryWrapper<Article> wrapper = new QueryWrapper<>();
            wrapper.eq("is_delete",0);
            articleMapper.selectList(wrapper)
                    .stream()
                    .forEach(articles -> redisService.cacheValue(KEY_ARTICLE_LIST, articles.getId(), articles, 3600));
        }
        for (String key : keys) {
            redisData.put(key, redisTemplate.opsForValue().get(key));
        }
        return redisData;
    }

    /**
     * 更新文章（
     * @param article
     * @return
     */
    @Override
    public boolean updArticle(Article article) {
        try {
            redisService.expire(KEY_ARTICLE_LIST, article.getId(), 3, TimeUnit.SECONDS);
            articleMapper.updateById(article);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
