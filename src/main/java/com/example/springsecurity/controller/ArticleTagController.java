package com.example.springsecurity.controller;

import com.example.springsecurity.pojo.ArticleTag;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.service.ArticleTagService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("增加文章标签")
    @PostMapping("/article/addArticleTag")
    public Response addArticleTag(@RequestBody ArticleTag articleTag) {
        return articleTagService.addArticleTag(articleTag);
    }

    @ApiOperation("删除文章标签")
    @PostMapping("/article/delArticleTag")
    public Response delArticleTag(@RequestBody ArticleTag articleTag) {
        return articleTagService.delArticleTag(articleTag);
    }

    @ApiOperation("根据id查询文章标题")
    @PostMapping("/article/selArticleTagById")
    public Response selArticleTagById(@RequestBody ArticleTag articleTag) {
        return articleTagService.selArticleTagById(articleTag);
    }

    @ApiOperation("查询所有的文章标签")
    @PostMapping("/article/allArticleTag")
    public Response allArticleTag(@RequestBody ArticleTag articleTag) {
        return articleTagService.allArticleTag(articleTag);
    }

    @ApiOperation("根据标签id更新文章标签")
    @PostMapping("/article/updArticleTag")
    public Response updArticleTag(@RequestBody ArticleTag articleTag) {
        return articleTagService.updArticleTag(articleTag);
    }
}
