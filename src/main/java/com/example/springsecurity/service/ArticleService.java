package com.example.springsecurity.service;

import com.example.springsecurity.pojo.Article;

import java.util.Map;

public interface ArticleService {
    boolean addArticle(Article article);
    boolean fakeDelArticle(Article article);
    boolean delArticle(Article article);
    Article selArticleById(Article article);
    public Map<String, Object> allRecycleArticle();
    Map<String, Object> allArticle();
    boolean updArticle(Article article);

}
