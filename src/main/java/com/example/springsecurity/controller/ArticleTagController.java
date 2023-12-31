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

    @PostMapping("/addArticleTag")
    public Response addArticleTag(@RequestBody ArticleTag articleTag) {
        return articleTagService.addArticleTag(articleTag);
    }

    @PostMapping("/delArticleTag")
    public Response delArticleTag(@RequestBody ArticleTag articleTag) {
        return articleTagService.delArticleTag(articleTag);
    }

    @PostMapping("/selArticleTagById")
    public Response selArticleTagById(@RequestBody ArticleTag articleTag) {
        return articleTagService.selArticleTagById(articleTag);
    }

    @PostMapping("/allArticleTag")
    public Response allArticleTag(@RequestBody ArticleTag articleTag) {
        return articleTagService.allArticleTag(articleTag);
    }

    @PostMapping("/updArticleTag")
    public Response updArticleTag(@RequestBody ArticleTag articleTag) {
        return articleTagService.updArticleTag(articleTag);
    }
}
