package com.example.springsecurity.service.Impt;

import com.example.springsecurity.mapper.ArticleMapper;
import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.service.ArticleService;
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
    private RedisService redisService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean addArticle(Article article) {
        try {
            articleMapper.insert(article);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delArticle(Article article) {
        try {
            redisService.expire(InitRedis.KEY_ARTICLE_LIST, article.getId(), 3, TimeUnit.SECONDS);
            articleMapper.deleteById(article.getId());
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
        Set<String> keys = redisTemplate.keys("DB:k_user_auth:*");
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
