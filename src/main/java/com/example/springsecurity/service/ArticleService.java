package com.example.springsecurity.service;

import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.pojo.DTO.ArticleDTO;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    boolean addArticle(Article article);
    boolean recoverArticle(Article article);
    boolean fakeDelArticle(Article article);
    boolean delArticle(Article article);
    Article selArticleById(Article article);
    public Map<String, Object> allDelArticle();
    Map<String, Object> allArticle();
    boolean updArticle(Article article);
    List<ArticleDTO> exchangeData();

    List<Article> paging(int pageNum);
}
