//package com.example.springsecurity.controller.data;
//
//import com.example.springsecurity.util.response.Code;
//import com.example.springsecurity.util.response.Msg;
//import com.example.springsecurity.pojo.Response;
//import com.example.springsecurity.service.ContentService;
//import com.example.springsecurity.service.TitleService;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//@CrossOrigin
//@RestController
//public class ContentController {
//    @Resource
//    private ContentService contentService;
//    @Resource
//    private TitleService titleService;
//
//    @Resource
//    private RedisTemplate redisTemplate;  //init
//    private static final String ALL_TITLE = "ALL_TITLE";
//    private static final String ALL_CONTENT = "ALL_CONTENT";
//    /**
//     * 获取文章
//     * @param content
//     * @return
//     */
//    @PostMapping("/article/getContentByid")
//    public Response getContentByid(@RequestBody Content content) {
//        try {
//            List<Content> contents = redisTemplate.opsForList().range(ALL_CONTENT, 0, -1);
//            Content content1 = new Content();
//            for (int i = 0;i < contents.size();i++) {
//                content1 = contents.get(i);
//                if(content1.getCid() == content.getCid()) {
//                    break;
//                }
//            }
//            return new Response(Code.GER_CONTENT_SUCCESS, Msg.GER_CONTENT_SUCCESS,content1);
//        } catch (Exception e) {
//            return new Response(Code.GER_CONTENT_ERROR, Msg.GER_CONTENT_ERROR,e);
//        }
//    }
//
//    /**
//     * 后台删除文章
//     */
//    @PostMapping("/article/deleteArticle")
//    public Response deleteContent(@RequestBody TitleAndContent tc) {
//        List<Content> contents = redisTemplate.opsForList().range(ALL_CONTENT,0,-1);
//        List<Title> titles = redisTemplate.opsForList().range(ALL_TITLE,0,-1);
//        for (int i = 0;i<contents.size();i++) {
//            Content content = contents.get(i);
//            Title title = titles.get(i);
//            if(content.getCid()== tc.getCid()&&title.getTid() == tc.getTid()) {
//                redisTemplate.opsForList().remove(ALL_TITLE,1,title);
//                redisTemplate.opsForList().remove(ALL_CONTENT,1,content);
//                break;
//            }
//        }
//        contentService.DeleteContent(tc);
//        titleService.DeleteTitle(tc);
//        return new Response(Code.DELETE_SUCCESS,Msg.DELETE_SUCCESS_MSG,true);
//    }
//
//
//    /**
//     * 后台修改文章内容'
//     * 循环list找到cid对应的值再更新
//     */
//    @PostMapping("/article/updateArticle")
//    public Response updateArticle(@RequestBody TitleAndContent tc) {
//        try {
//            Title newTitle = new Title();
//            Content newContent = new Content();
//            newTitle.setAll( tc.getTid(), tc.getTitle(), tc.getDigest(), tc.getDate());
//            newContent.setAll(tc.getCid(), tc.getContent());
//            List<Content> contents = redisTemplate.opsForList().range(ALL_CONTENT,0,-1);
//            for (int i = 0; i < contents.size(); i++) {
//                Content content = contents.get(i);
//                // 检查是否满足条件
//                if (content.getCid() == tc.getCid()) {
//                    // 更新该行的值
//                    redisTemplate.opsForList().set(ALL_CONTENT, i, newContent);
//                    redisTemplate.opsForList().set(ALL_TITLE, i, newTitle);
//                    break;
//                }
//            }
//            contentService.UpdateContent(tc);
//            titleService.UpdateTitle(tc);
//            return new Response(Code.UPDATE_SUCCESS,Msg.UPDATE_SUCCESS_MSG,true);
//        } catch (Exception e) {
//            return new Response(Code.UPDATE_ERR,Msg.UPDATE_ERR_MSG,false);
//        }
//    }
//
//    /**
//     * 新增一篇文章
//     */
//    @PostMapping("/article/InsertArticle")
//    public Response InsertArticle(@RequestBody TitleAndContent tc) {
//        try {
//            Content newContent = new Content();
//            Title newTitle = new Title();
//            //DB
//            titleService.InsertTitle(tc);
//            contentService.InsertContent(tc);
//            //Redis
//            long size = redisTemplate.opsForList().size(ALL_CONTENT);
//            List<Content> content = redisTemplate.opsForList().range(ALL_CONTENT,size-1,size-1);
//
//            newContent.setAll(content.get(0).getCid()+1,tc.getContent());
//            newTitle.setAll(content.get(0).getCid()+1, tc.getTitle(), tc.getDigest(), tc.getDate());
//
//            redisTemplate.opsForList().rightPush(ALL_TITLE, newTitle);
//            redisTemplate.opsForList().rightPush(ALL_CONTENT, newContent);
//        return new Response(Code.INSERT_CONTENT_SUCCESS,Msg.INSERT_CONTENT_SUCCESS,true);
//        } catch (Exception e) {
//            return new Response(Code.INSERT_CONTENT_ERROR,Msg.INSERT_CONTENT_ERROR,false);
//        }
//    }
//
//    /**
//     * 存入所有文章到redis
//     * rightPushAll()从尾部开始添加
//     */
////    @PostMapping("/InitRedis")
//    @PostConstruct
//    public Response InsertAllArticle() {
//        try {
//            redisTemplate.delete(ALL_TITLE);
//            redisTemplate.delete(ALL_CONTENT);
//            List<Content> allContent = contentService.getAllContent();
//            List<Title> allTitle = titleService.allTitle();
//
//            redisTemplate.opsForList().rightPushAll(ALL_TITLE, allTitle);
//            redisTemplate.opsForList().rightPushAll(ALL_CONTENT, allContent);
//
//            redisTemplate.expire(ALL_TITLE,  Long.MAX_VALUE, TimeUnit.SECONDS);
//            redisTemplate.expire(ALL_CONTENT, Long.MAX_VALUE, TimeUnit.SECONDS);
////            redisTemplate.expire(ALL_TITLE, 1, TimeUnit.DAYS);
////            redisTemplate.expire(ALL_CONTENT, 1, TimeUnit.DAYS);
//
//            return new Response(Code.UPDATE_SUCCESS, Msg.UPDATE_SUCCESS_MSG,true);
//        } catch (Exception e) {
//            return new Response(Code.UPDATE_SUCCESS, Msg.UPDATE_SUCCESS_MSG,e);
//        }
//    }
//
////    @PostMapping("/todo")
////    public Response todo(@RequestBody Content content) {
////        List<Content> content1 = redisTemplate.opsForList().range(ALL_CONTENT,content.getCid(),content.getCid());
////
////        System.out.println(content1);
////        return new Response(Code.GET_SUCCESS,Msg.GER_SUCCESS_MSG,content1);
////    }
//
//
//}
