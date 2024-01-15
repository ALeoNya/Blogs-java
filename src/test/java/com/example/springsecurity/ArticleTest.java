package com.example.springsecurity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springsecurity.mapper.ArticleMapper;
import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.service.ArticleService;
import com.example.springsecurity.util.redis.service.RedisService;
import com.example.springsecurity.util.redis.config.InitRedis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        Article newArticle = new Article();
        newArticle.setUserId(1);
        newArticle.setArticleContent("bbb1");
        newArticle.setArticleTitle("bbb1");
        newArticle.setArticleAbstract("bbb1");
        articleService.addArticle(newArticle);
    }

    @Test
    public void getArticle() {
        Article article = new Article();
        article.setId(1);
        System.out.println(redisService.containsKey(InitRedis.KEY_ARTICLE_LIST, article.getId()));
//        System.out.println(articleService.selArticleById(article));
    }

    @Test
    public void delArticle() {
        Article newArticle = new Article();
        newArticle.setId(4);
        articleService.delArticle(newArticle);
    }

    @Test
    public void fakedelArticle() {
        Article newArticle = new Article();
        newArticle.setId(10);
//        articleMapper.fakeDelArticle(newArticle);
        articleService.fakeDelArticle(newArticle);
        System.out.println(articleMapper.selectById(10));
    }

    @Test
    public void QueryWrapparArticle() {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete",0);
        List<Article> articles = articleMapper.selectList(wrapper);
        System.out.println(articles);
    }

    @Test
    public void updateArticle() {
        Article newArticle = new Article();
        newArticle.setId(10);
        newArticle.setCategoryId(1);
        articleMapper.updateById(newArticle);
        System.out.println(articleMapper.selectById(10));
    }

}
