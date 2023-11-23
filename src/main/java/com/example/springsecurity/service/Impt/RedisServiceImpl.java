package com.example.springsecurity.service.Impt;

import com.example.springsecurity.pojo.UserAuth;
import com.example.springsecurity.service.RedisService;
import com.example.springsecurity.util.redis.config.InitRedis;
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
     * 添加 key:Object 实体类缓存
     */
    public boolean cacheValue(String prefix, int key, Object value, long time) {
        String cachekey = prefix + key;
        try {
            ValueOperations ops = redisTemplate.opsForValue();
            ops.set(cachekey, value);
            if(time>0) {
                redisTemplate.expire(cachekey, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable e) {
            log.error("缓存存入失败key:[{}] value:[{}]", key, value);
        }
        return false;
    }

    /**
     * 添加 key:string String缓存
     */
    public boolean cacheStringValue(String prefix, int k, String value, long time) {
        String key = prefix +k;
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
     * key:list list缓存
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

    /**
     * key:ArrayList<T> 泛型缓存
     */
    public <T> boolean cacheTest(String prefix, int key, ArrayList<T> value, long time) {
        String cacheKey = prefix + key;
        try {
            ValueOperations ops = redisTemplate.opsForValue();
            ops.set(key, value);
            if(time>0) {
                redisTemplate.expire(cacheKey, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable e) {
            log.error("缓存存入失败key:[{}] value:[{}]", key, value);
        }
        return false;
    }

    /**
     * 判断key是否存在
     */
    public boolean containsKey(String prefix, String k) {
        String key = null;
        try {
            key = prefix + k;
            return redisTemplate.hasKey(key);
        } catch (Throwable e) {
            log.error("判断缓存存在失败key:[{}],错误信息 Codeor[{}]", key, e);
        }
        return false;
    }

    /**
     * 根据key获取缓存
     */
    public UserAuth getUserAuth(String key) {
        return (UserAuth) redisTemplate.opsForValue().get(InitRedis.KEY_USERAUTH_LIST+key);
    }

    /**
     * 设置key的过期时间
     */
    public boolean expire(String prefix, String k, long timeout, TimeUnit unit) {
        try {
            String key = prefix + k;
            redisTemplate.expire(key, timeout, unit);
            return true;
        }catch (Throwable e){
            log.error("移除list缓存失败 key[" + prefix + k + "], Codeor[" + e + "]");
        }
        return false;
    }

    /**
     * 根据 key 移除缓存
     */
    public boolean removeByKey(String prefix, String k) {
        try {
            String key = prefix + k;
            redisTemplate.delete(key);
            return true;
        }catch (Throwable e){
            log.error("移除list缓存失败 key[" + prefix + k + "], Codeor[" + e + "]");
        }
        return false;
    }
}
