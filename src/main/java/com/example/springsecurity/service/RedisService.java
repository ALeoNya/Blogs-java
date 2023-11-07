package com.example.springsecurity.service;

import java.util.List;

public interface RedisService {
    public boolean cacheValue(String key, String value, long time);
    public boolean cacheList(int k, List v, long time);
}
