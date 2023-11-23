package com.example.springsecurity.service;

import com.example.springsecurity.pojo.UserAuth;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public interface RedisService {
    /**
     * 添加缓存
     */
    public boolean cacheValue(String prefix, int key, Object value, long time);

    public boolean cacheStringValue(String prefix, int k, String value, long time);

    public boolean cacheList(int k, String v, long time);

    public <T> boolean cacheTest(String prefix, int key, ArrayList<T> value, long time);

    /**
     * 判断缓存是否存在
     */
    //泛型
    public boolean containsKey(String prefix, String key);

    /**
     * 获取存在的缓存
     */
    public UserAuth getUserAuth(String key);

    /**
     * 过期处理
     */
    public boolean expire(String prefix, String k, long timeout, TimeUnit unit);

    /**
     * 删除缓存(考虑并发问题
     */
    public boolean removeByKey(String prefix, String k);
}
