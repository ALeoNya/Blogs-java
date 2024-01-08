package com.example.springsecurity.service.Impt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springsecurity.mapper.ArticleMapper;
import com.example.springsecurity.mapper.ArticleTagMapper;
import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.pojo.ArticleTag;
import com.example.springsecurity.service.ArticleService;
import com.example.springsecurity.service.ArticleTagService;
import com.example.springsecurity.util.redis.service.RedisService;
import com.example.springsecurity.util.redis.config.InitRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private ArticleTagMapper articleTagMapper;


    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean addArticle(Article article) {
        try {
            System.out.println(article);
            // 对数据库操作
            articleMapper.insert(article);
            // 对Redis操作
            redisService.cacheValue(InitRedis.KEY_ARTICLE_LIST, article.getId(), article, 3600);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 假删除
     * @param article
     * @return
     */
    @Override
    public boolean fakeDelArticle(Article article) {
        try {
            articleMapper.fakeDelArticle(article);
        } catch (RuntimeException ignored) {
            return false;
        }
        return true;
    }


    @Override
    public boolean delArticle(Article article) {
        try {
            redisService.expire(InitRedis.KEY_ARTICLE_LIST, article.getId(), 3, TimeUnit.SECONDS);
            articleMapper.deleteById(article.getId());
            // TODO 需要同时删除article_tag表(根据article_id查询删除
            QueryWrapper<ArticleTag> qw_ArticleTag = new QueryWrapper<>();
            qw_ArticleTag.eq("article_id",article.getId());
            articleTagMapper.delete(qw_ArticleTag);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Article selArticleById(Article article) {
        try {
            if(redisService.containsKey(InitRedis.KEY_ARTICLE_LIST, article.getId())) {
                return redisService.getArticle(article);
            }
        } catch (Exception e) {
            return null;
        }
        redisService.cacheValue(InitRedis.KEY_ARTICLE_LIST, article.getId(), articleMapper.selectById(article.getId()), 360000);
        return redisService.getArticle(article);
    }

    @Override
    public Map<String, Object> allArticle() {
        Map<String, Object> redisData = new HashMap<>();
        Set<String> keys = redisTemplate.keys("DB:k_article:*");
        
        if(keys == null) {
            articleMapper.selectList(null)
                    .stream()
                    .forEach(article -> redisService.cacheValue(InitRedis.KEY_ARTICLE_LIST, article.getId(), article, 3600));
        }
        for (String key : keys) {
            redisData.put(key, redisTemplate.opsForValue().get(key));
        }
        return redisData;
    }

    @Override
    public Map<String, Object> allRecycleArticle() {
        Map<String, Object> redisData = new HashMap<>();
        Set<String> keys = redisTemplate.keys("DB:k_article:*");
        if(keys == null) {
            
            articleMapper.selectList(null)
                    .stream()
                    .forEach(article -> redisService.cacheValue(InitRedis.KEY_ARTICLE_LIST, article.getId(), article, 3600));
        }
        for (String key : keys) {
            redisData.put(key, redisTemplate.opsForValue().get(key));
        }
        return redisData;
    }

    @Override
    public boolean updArticle(Article article) {
        try {
            redisService.expire(InitRedis.KEY_ARTICLE_LIST, article.getId(), 3, TimeUnit.SECONDS);
            articleMapper.updateById(article);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
