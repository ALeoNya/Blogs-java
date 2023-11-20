package com.example.springsecurity.service;

import java.util.ArrayList;

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
    public boolean containsKey(String key);
    //多态（对应不同的实体类
    public boolean containsResourceKey(String key);
    public boolean containsRoleKey(String key);
    public boolean containsRoleResourceKey(String key);
    public boolean containsUserAuthKey(String key);
    public boolean containsUserRoleKey(String key);
    /**
     * 获取存在的缓存
     */

}
