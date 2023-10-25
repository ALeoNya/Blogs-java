//package com.example.springsecurity.controller.data;
//
//import com.example.springsecurity.util.response.Code;
//import com.example.springsecurity.util.response.Msg;
//import com.example.springsecurity.pojo.Response;
//import com.example.springsecurity.service.TitleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import java.util.List;
//@CrossOrigin
//@RestController
//public class TitleController {
//    @Autowired
//    private TitleService titleService;
//    @Resource
//    private RedisTemplate redisTemplate;  //init
//    private static final String ALL_TITLE = "ALL_TITLE";
//    private static final String ALL_CONTENT = "ALL_CONTENT";
//    /**
//     * 博客展示栏
//     * @return
//     */
//    @GetMapping("/getTitle")
//    public Response getTitle() {
//        return new Response(Code.SUCCESS, Msg.GER_SUCCESS_MSG,titleService.allTitle());
//    }
//
//
//    @GetMapping("/getSevenTitle/{pageNumber}")
//    public Response getSevenTitle(@PathVariable int pageNumber) {
//        return new Response(Code.SUCCESS, Msg.GER_SUCCESS_MSG,titleService.getSevenTitle(pageNumber));
//    }
//
//    /**
//     * 后台展示栏
//     * @return
//     */
//    @GetMapping("/getAllTitle")
//    public Response getAllTitle() {
//        List<Title> allTitle = redisTemplate.opsForList().range(ALL_TITLE,0,-1);
//        System.out.println("Redis的数据是"+allTitle);
//        if(allTitle==null) {
//            allTitle = titleService.allTitle();
//            System.out.println("SQL的数据是"+allTitle);
//        }
////        List<Title> allTitle = titleService.allTitle();
//        return new Response(Code.SUCCESS, Msg.GER_SUCCESS_MSG,allTitle);
//    }
//
//    /**
//     * 更新
//     * @param titleAndContent
//     * @return
//     */
//    @PostMapping("/updateTitle")
//    public Response updateTitle(@RequestBody TitleAndContent titleAndContent) {
//        return new Response(Code.UPDATE_SUCCESS,Msg.UPDATE_SUCCESS_MSG,titleService.UpdateTitle(titleAndContent));
//    }
//
//
//}
