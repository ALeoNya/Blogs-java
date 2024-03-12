package com.example.springsecurity.controller;

import com.example.springsecurity.pojo.Article;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.response.Code;
import com.example.springsecurity.response.Msg;
import com.example.springsecurity.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController  //将方法的返回值写入到response的body区域，返回给客户端
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @ApiOperation("新增文章")
    @PostMapping("/addArticle")
    public Response addArticle(@RequestBody Article article) {
        //对数据库和redis分别进行数据添加操作
        if(articleService.addArticle(article)) {
            return new Response(Code.SUCCESS, Msg.ADD_SUCCESS_MSG, "添加文章ing");
        }
        return new Response(Code.FAILED, Msg.ADD_FAIL_MSG, "格式错误");
    }

    @ApiOperation("删除文章")
    @PostMapping("/article/delArticle")
    public Response delArticle(@RequestBody Article article) {
        if(articleService.delArticle(article)) {
            return new Response(Code.SUCCESS, Msg.DEL_SUCCESS_MSG, "删除文章成功");
        }
        return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, "删除文章失败，请重试");
    }

    @ApiOperation("假删除文章")
    @PostMapping("/article/fakeDelArticle")
    public Response fakeallDelArticle(@RequestBody Article article) {
        if(articleService.fakeDelArticle(article)) {
            return new Response(Code.SUCCESS, Msg.DEL_SUCCESS_MSG, "文章已移入回收站");
        }
        return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, "文章移入回收站失败，请重试");
    }

    @ApiOperation("恢复被假删除文章")
    @PostMapping("/article/recoverArticle")
    public Response recoverArticle(@RequestBody Article article) {
        if(articleService.recoverArticle(article)) {
            return new Response(Code.SUCCESS, Msg.DEL_SUCCESS_MSG, "恢复文章成功");
        }
        return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, "恢复文章失败，请重试");
    }

    @ApiOperation("查询所有文章")
    @PostMapping("/article/allArticle")
//    @PreAuthorize("hasAuthority('/article/getListArticle')")
    public Response allArticle() {
        try {
            return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, articleService.allArticle());
        } catch (Exception e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, false);
        }
    }

    @ApiOperation("查询所有文章ArticleDTO（改变文章时间格式）")
    @PostMapping("/article/allArticleDTO")
    public Response allArticleDTO() {
        try {
            return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, articleService.exchangeData());
        } catch (Exception e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, false);
        }
    }

//    @PostMapping("/article/getArticleByLast")
//    public Response getArticleByLast() {
//
//    }

    @ApiOperation("查询所有被假删除的文章")
    @PostMapping("/article/allDelArticle")
    public Response allDelArticle() {
        try {
            return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, articleService.allDelArticle());
        } catch (Exception e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, false);
        }
    }

    @ApiOperation("根据id查询文章")
    @PostMapping("/selArticleById")
    public Response selArticleById(@RequestBody Article article) {
        try {
            return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, articleService.selArticleById(article));
        } catch (Exception e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, false);
        }
    }

    @ApiOperation("根据id新增文章")
    @PostMapping("/article/updArticle")
    public Response updArticle(@RequestBody Article article) {
        if(articleService.updArticle(article)) {
            return new Response(Code.SUCCESS, Msg.UPD_SUCCESS_MSG, "更新已完成，正在跳转...");
        }
        return new Response(Code.FAILED, Msg.UPD_FAIL_MSG, "请重新更新");
    }

    @ApiOperation("根据前端传回的页数分页")
    @GetMapping("/article/pagingArticle/{pageNum}")
    public Response pagingArticle(@PathVariable("pageNum") int pageNum) {
        return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, articleService.paging(pageNum));
    }
//    @ApiOperation("根据前端传回的页数分页")
//    @PostMapping("/article/pagingArticle")
//    public Response pagingArticle(@RequestParam("pageNum") int pageNum) {
//        return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, articleService.paging(pageNum));
//    }
}
