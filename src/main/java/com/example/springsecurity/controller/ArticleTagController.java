package com.example.springsecurity.controller;

import com.example.springsecurity.pojo.ArticleTag;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ArticleTagController {
    @Autowired
    private ArticleTagService articleTagService;

    @PostMapping("/article/addArticleTag")
    public Response addArticleTag(@RequestBody ArticleTag articleTag) {
        return articleTagService.addArticleTag(articleTag);
    }

    @PostMapping("/article/delArticleTag")
    public Response delArticleTag(@RequestBody ArticleTag articleTag) {
        return articleTagService.delArticleTag(articleTag);
    }

    @PostMapping("/article/selArticleTagById")
    public Response selArticleTagById(@RequestBody ArticleTag articleTag) {
        return articleTagService.selArticleTagById(articleTag);
    }

    @PostMapping("/article/allArticleTag")
    public Response allArticleTag(@RequestBody ArticleTag articleTag) {
        return articleTagService.allArticleTag(articleTag);
    }

    @PostMapping("/article/updArticleTag")
    public Response updArticleTag(@RequestBody ArticleTag articleTag) {
        return articleTagService.updArticleTag(articleTag);
    }
}
