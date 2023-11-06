package com.example.springsecurity.service;

public interface RedisService {
    public boolean cacheValue(String key, String value, long time);
}
