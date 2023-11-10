package com.example.springsecurity.service.Impt;

import com.example.springsecurity.service.RedisService;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service("RedisService")
public class RedisServiceImpl implements RedisService {
    /**
     * 自定义 key 三种
     *  String key:String value         普通key:value
     *  String key:Set<String> set      key:set集合
     *  String key:List<String> list    key:list集合
     */
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RedisTemplate redisTemplate;

    private static final String KEY_PREFIX_KEY = "info:bear:key";
    private static final String KEY_PREFIX_SET = "info:bear:set";
    private static final String KEY_PREFIX_LIST = "info:bear:list";
    /**
     * 添加 key:string 缓存
     */
    public boolean cacheValue(int k, Object value, long time) {
        String key = KEY_PREFIX_LIST +k;
        try {
            ValueOperations ops = redisTemplate.opsForValue();
            ops.set(key,value);
            if(time>0) {
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable e) {
            log.error("缓存存入失败key:[{}] value:[{}]", key, value);
        }
        return false;
    }

    /**
     * 添加 key:string 缓存
     */
    public boolean cacheStringValue(int k, String value, long time) {
        String key = KEY_PREFIX_KEY +k;
        try {
            ValueOperations ops = redisTemplate.opsForValue();
            ops.set(key,value);
            if(time>0) {
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable e) {
            log.error("缓存存入失败key:[{}] value:[{}]", key, value);
        }
        return false;
    }

    /**
     * list 缓存
     * @param k key
     * @param v value
     * @param time 时间
     * @return true/false
     */
    public boolean cacheList(int k, String v, long time) {
        try {
            String key = KEY_PREFIX_LIST + k;
            ListOperations opsForList = redisTemplate.opsForList();
            //此处为right push 方法/ 也可以 left push ..
            opsForList.rightPush(key,v);
            if (time > 0){
                redisTemplate.expire(key,time,TimeUnit.SECONDS);
            }
            return true;
        }catch (Throwable e){
            log.error("缓存list失败 当前 key:[{}],失败原因 [{}]", k, e);
        }
        return false;
    }
}
