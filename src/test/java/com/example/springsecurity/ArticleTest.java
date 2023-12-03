package com.example.springsecurity;

import com.example.springsecurity.mapper.ArticleMapper;
import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.service.ArticleService;
import com.example.springsecurity.util.redis.service.RedisService;
import com.example.springsecurity.util.redis.config.InitRedis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleTest {
    @Autowired
    private RedisService redisService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleMapper articleMapper;
    @Test
    public void addArticle() {
        Article article = new Article();
        article.setId(1);
//        System.out.println(articleService.selArticleById(article));
        redisService.cacheValue(InitRedis.KEY_ARTICLE_LIST, article.getId(), articleMapper.selectById(article.getId()), 360000);
    }

    @Test
    public void getArticle() {
        Article article = new Article();
        article.setId(1);
        System.out.println(redisService.containsKey(InitRedis.KEY_ARTICLE_LIST, article.getId()));
//        System.out.println(articleService.selArticleById(article));
    }
}
