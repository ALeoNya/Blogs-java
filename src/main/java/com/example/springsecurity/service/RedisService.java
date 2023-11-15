package com.example.springsecurity.service;

import java.util.ArrayList;

public interface RedisService {
    public boolean cacheValue(String prefix, int key, Object value, long time);

    public boolean cacheStringValue(String prefix, int k, String value, long time);

    public boolean cacheList(int k, String v, long time);

    public <T> boolean cacheTest(String prefix, int key, ArrayList<T> value, long time);
}
