package com.example.springsecurity.service.Impt;

import com.example.springsecurity.mapper.CategoryMapper;
import com.example.springsecurity.pojo.Category;
import com.example.springsecurity.pojo.Response;
import com.example.springsecurity.response.Code;
import com.example.springsecurity.response.Msg;
import com.example.springsecurity.service.CategoryService;
import com.example.springsecurity.util.redis.config.InitRedis;
import com.example.springsecurity.util.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public Response addCategory(Category category) {
        int key = category.getId();
        try {
            categoryMapper.insert(category);
            redisService.cacheValue(InitRedis.KEY_CATEGORY_LIST, key, category, 3600);
            if(redisService.getObject(InitRedis.KEY_CATEGORY_LIST, key) == null) {
                return new Response(Code.FAILED, Msg.ADD_FAIL_MSG, "插入数据为空");
            }
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.ADD_FAIL_MSG, e);
        }
        return new Response(Code.SUCCESS, Msg.ADD_SUCCESS_MSG, category);
    }

    @Override
    public Response delCategory(Category category) {
        int key = category.getId();
        try {
            redisService.expire(InitRedis.KEY_CATEGORY_LIST, key, 3, TimeUnit.SECONDS);
            categoryMapper.deleteById(key);
            return new Response(Code.SUCCESS, Msg.DEL_SUCCESS_MSG, redisService.containsKey(InitRedis.KEY_CATEGORY_LIST, key));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.DEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response selCategoryById(Category category) {
        int key = category.getId();
        try {
            if(redisService.containsKey(InitRedis.KEY_CATEGORY_LIST, key)) {
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getObject(InitRedis.KEY_CATEGORY_LIST, key));
            } else {
                Category get = categoryMapper.selectById(key);
                //查到null值缓存到redis设置过期时间为6min
                redisService.cacheValue(InitRedis.KEY_CATEGORY_LIST, key, get, 360);
                if(get == null) {
                    return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, "你查询的是一个空值");
                }
                return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.getObject(InitRedis.KEY_CATEGORY_LIST, key));
            }
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response allCategory() {
        try {
            return new Response(Code.SUCCESS, Msg.SEL_SUCCESS_MSG, redisService.allCache(InitRedis.KEY_CATEGORY_LIST));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.SEL_FAIL_MSG, e);
        }
    }

    @Override
    public Response updCategory(Category category) {
        int key = category.getId();
        try {
            redisService.expire(InitRedis.KEY_CATEGORY_LIST, key, 3, TimeUnit.SECONDS);
            categoryMapper.updateById(category);
            return new Response(Code.SUCCESS, Msg.UPD_SUCCESS_MSG, redisService.getObject(InitRedis.KEY_CATEGORY_LIST, key));
        } catch (RuntimeException e) {
            return new Response(Code.FAILED, Msg.UPD_FAIL_MSG, e);
        }
    }
}
