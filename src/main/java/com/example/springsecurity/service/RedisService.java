package com.example.springsecurity.service;

import java.util.ArrayList;
import java.util.List;

public interface RedisService {
    public boolean cacheValue(int key, Object value, long time);
    public boolean cacheStringValue(int k, String value, long time);
    public boolean cacheList(int k, String v, long time);
}
