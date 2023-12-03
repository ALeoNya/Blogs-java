package com.example.springsecurity.service;

import com.example.springsecurity.pojo.Article;

import java.util.Map;

public interface ArticleService {
    boolean addArticle(Article article);
    boolean delArticle(Article article);
    Article selArticleById(Article article);
    Map<String, Object> allArticle();
    boolean updArticle(Article article);

}
