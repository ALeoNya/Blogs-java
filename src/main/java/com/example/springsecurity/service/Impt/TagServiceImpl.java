package com.example.springsecurity.service.Impt;

import com.example.springsecurity.mapper.TagMapper;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.pojo.Tag;
import com.example.springsecurity.response.Code;
import com.example.springsecurity.response.Msg;
import com.example.springsecurity.service.TagService;
import com.example.springsecurity.util.redis.config.InitRedis;
import com.example.springsecurity.util.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("TagService")
public class TagServiceImpl implements TagService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private TagMapper tagMapper;
    @Override
    public Response addTag(Tag tag) {
        try {
            if(tag.getTagName() == null) {
                return new Response(Code.FAILED, Msg.ADD_FAIL_MSG, "插入数据为空");
            }
            tagMapper.insert(tag);
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.ADD_FAIL_MSG, e);
        }
        return new Response(Code.SUCCESS, Msg.ADD_SUCCESS_MSG, tag);
    }

    @Override
    public Response delTag(Tag tag) {
        int key = tag.getId();
        try {
            tagMapper.deleteById(key);
            return new Response(Code.SUCCESS, Msg.DEL_SUCCESS_MSG, tag);
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response selTagById(Tag tag) {
        int key = tag.getId();
        try {
            if(redisService.containsKey(InitRedis.KEY_TAG_LIST, key)) {
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getObject(InitRedis.KEY_TAG_LIST, key));
            } else {
                Tag get = tagMapper.selectById(key);
                //查到null值缓存到redis设置过期时间为6min
                if(get == null) {
                    redisService.cacheValue(InitRedis.KEY_TAG_LIST, key, get, 360);
                    return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, "你查询的是一个空值");
                }
                redisService.cacheValue(InitRedis.KEY_TAG_LIST, key, get, 3600);
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getObject(InitRedis.KEY_TAG_LIST, key));
            }
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response allTag() {
        try {
            return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, tagMapper.selectList(null));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response updTag(Tag tag) {
        int key = tag.getId();
        try {
            tagMapper.updateById(tag);
            return new Response(Code.SUCCESS, Msg.UPD_SUCCESS_MSG, tag);
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.UPD_FAIL_MSG, e);
        }
    }
}
