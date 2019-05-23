package com.qhbd.config.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.cache.Cache;
import java.util.concurrent.Callable;

/**
 * 二级缓存
 */
public class LoalRedisCache extends RedisCache {

    private static final Logger LOGER = LoggerFactory.getLogger(LoalRedisCache.class);

    private Cache localCache ;

    public LoalRedisCache(Cache localCache ,String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig){
        super(name,cacheWriter,cacheConfig);
        this.localCache = localCache;
    }

    public LoalRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig){

        super(name,cacheWriter,cacheConfig);
    }

    public LoalRedisCache(Cache localCache,RedisCache redisCache){
        super(redisCache.getName(),redisCache.getNativeCache(),redisCache.getCacheConfiguration());
        this.localCache = localCache;
    }

    @Override
    public synchronized <T> T get(Object key, Callable<T> valueLoader) {
        //先读取本地一级缓存
        T value = localCache.get(key, valueLoader);
        if (value == null) {
            //本地一级缓存不存在，读取redis二级缓存
            value = super.get(key, valueLoader);
            if (value != null) {
                //redis二级缓存存在，存入本地一级缓存
                localCache.put(key, value);
            }
        }
        return value;
    }

    @Override
    public void put(Object key, Object value) {
        LOGER.info("put local key {}",key);
        localCache.put(key, value);
        LOGER.info("put redis key {}",key);
        super.put(key, value);

    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        ValueWrapper vw1 = localCache.putIfAbsent(key, value);
        ValueWrapper vw2 = super.putIfAbsent(key, value);
        return vw1 == null ? vw2 : vw1;
    }

    @Override
    public void evict(Object key) {
        localCache.evict(key);
        super.evict(key);
    }

    @Override
    public void clear() {
        localCache.clear();
        super.clear();
    }

    @Override
    public ValueWrapper get(Object key) {
        LOGER.info("get from local key {}",key);
        ValueWrapper valueWrapper = localCache.get(key);
        if (valueWrapper == null) {
            LOGER.info("get from redis key {}",key);
            valueWrapper = super.get(key);
            if (valueWrapper != null) {
                LOGER.info("put  local key because local key is not exit but redis exit {}",key);
                localCache.put(key, valueWrapper.get());
            }
        }
        return valueWrapper;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        T value = localCache.get(key, type);
        if (value == null) {
            value = super.get(key, type);
            if (value != null) {
                localCache.put(key, value);
            }
        }
        return value;
    }

}
