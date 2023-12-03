package com.example.springsecurity.service;

import com.example.springsecurity.pojo.ArticleTag;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.RoleResource;

public interface ArticleTagService {
    public Response addArticleTag(ArticleTag articleTag);
    public Response delArticleTag(ArticleTag articleTag);
    public Response selArticleTagById(ArticleTag articleTag);
    public Response allArticleTag(ArticleTag articleTag);
    public Response updArticleTag(ArticleTag articleTag);
}
