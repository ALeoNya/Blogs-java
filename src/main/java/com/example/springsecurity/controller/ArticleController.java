package com.example.springsecurity.controller;

import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.response.Code;
import com.example.springsecurity.response.Msg;
import com.example.springsecurity.service.ArticleService;
import com.example.springsecurity.util.redis.config.InitRedis;
import com.example.springsecurity.util.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController  //将方法的返回值写入到response的body区域，返回给客户端
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/addArticle")
    public Response addArticle(@RequestBody Article article) {
        //对数据库和redis分别进行数据添加操作
        if(articleService.addArticle(article)) {
            return new Response(Code.SUCCESS, Msg.ADD_SUCCESS_MSG, "添加文章ing");
        }
        return new Response(Code.FAILED, Msg.ADD_FAIL_MSG, "格式错误");
    }

    @PostMapping("/ article/delArticle")
    public Response delArticle(@RequestBody Article article) {
        if(articleService.delArticle(article)) {
            return new Response(Code.SUCCESS, Msg.DEL_SUCCESS_MSG, true);
        }
        return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, false);
    }


    @PostMapping("/article/fakeDelArticle")
    public Response fakeallDelArticle(@RequestBody Article article) {
        if(articleService.fakeDelArticle(article)) {
            return new Response(Code.SUCCESS, Msg.DEL_SUCCESS_MSG, true);
        }
        return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, false);
    }

    @PostMapping("/article/allArticle")
//    @PreAuthorize("hasAuthority('/article/getListArticle')")
    public Response allArticle() {
        try {
            return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, articleService.allArticle());
        } catch (Exception e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, false);
        }
    }

    @PostMapping("/article/allDelArticle")
    public Response allDelArticle() {
        try {
            return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, articleService.allDelArticle());
        } catch (Exception e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, false);
        }
    }

    @PostMapping("/selArticleById")
    public Response selArticleById(@RequestBody Article article) {
        try {
            return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, articleService.selArticleById(article));
        } catch (Exception e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, false);
        }
    }

    @PostMapping("/updArticle")
    public Response updArticle(@RequestBody Article article) {
        if(articleService.updArticle(article)) {
            return new Response(Code.SUCCESS, Msg.UPD_SUCCESS_MSG, "完成咯！");
        }
        return new Response(Code.FAILED, Msg.UPD_FAIL_MSG, "请重新更新");
    }
}
