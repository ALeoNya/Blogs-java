package com.example.springsecurity.service.Impt;

import com.example.springsecurity.mapper.ArticleTagMapper;
import com.example.springsecurity.pojo.ArticleTag;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.response.Code;
import com.example.springsecurity.response.Msg;
import com.example.springsecurity.service.ArticleTagService;
import com.example.springsecurity.util.redis.config.InitRedis;
import com.example.springsecurity.util.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.TimeUnit;

@Service("ArticleTagService")
public class ArticleTagServiceImpl implements ArticleTagService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public Response addArticleTag(ArticleTag articleTag) {
        int key = articleTag.getId();
        try {
            articleTagMapper.insert(articleTag);
            redisService.cacheValue(InitRedis.KEY_ARTICLETAG_LIST, key, articleTag, 3600);
            if(redisService.getObject(InitRedis.KEY_ARTICLETAG_LIST, key) == null) {
                return new Response(Code.FAILED, Msg.ADD_FAIL_MSG, "插入数据为空");
            }
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.ADD_FAIL_MSG, e);
        }
        return new Response(Code.SUCCESS, Msg.ADD_SUCCESS_MSG, articleTag);
    }

    @Override
    public Response delArticleTag(ArticleTag articleTag) {
        int key = articleTag.getId();
        try {
            redisService.expire(InitRedis.KEY_ARTICLETAG_LIST, key, 3, TimeUnit.SECONDS);
            articleTagMapper.deleteById(key);
            return new Response(Code.SUCCESS, Msg.DEL_SUCCESS_MSG, redisService.containsKey(InitRedis.KEY_ARTICLETAG_LIST, key));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response selArticleTagById(ArticleTag articleTag) {
        int key = articleTag.getId();
        try {
            if(redisService.containsKey(InitRedis.KEY_ARTICLETAG_LIST, key)) {
                //判断Redis存在，true返回Redis查询结果
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getObject(InitRedis.KEY_ARTICLETAG_LIST, key));
            } else {
                //false,从DB存入Redis
                ArticleTag get = articleTagMapper.selectById(key);
                if(get == null) {
                    //查到null值缓存到redis设置过期时间为6min
                    redisService.cacheValue(InitRedis.KEY_ARTICLETAG_LIST, key, get, 360);
                    return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, "你查询的是一个空值");
                }
                redisService.cacheValue(InitRedis.KEY_ARTICLETAG_LIST, key, get, 3600);
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getObject(InitRedis.KEY_ARTICLETAG_LIST, key));
            }
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response allArticleTag(ArticleTag articleTag) {
        try {
            return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.allCache(InitRedis.KEY_ARTICLETAG_LIST));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response updArticleTag(ArticleTag articleTag) {
        int key = articleTag.getId();
        try {
            //过期后更新DB
            redisService.expire(InitRedis.KEY_ARTICLETAG_LIST, key, 3, TimeUnit.SECONDS);
            articleTagMapper.updateById(articleTag);
            return new Response(Code.SUCCESS, Msg.UPD_SUCCESS_MSG, redisService.getObject(InitRedis.KEY_ARTICLETAG_LIST, key));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.UPD_FAIL_MSG, e);
        }
    }
}
