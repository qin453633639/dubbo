package com.qhbd.config.redis;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.time.Duration;

/**
 * Created by qinwei on 2019/5/20.
 */
public class MyRedisCacheManager extends RedisCacheManager {


    public MyRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration, true);
    }

    @Override
    protected RedisCache getMissingCache(String name) {
        RedisCache redisCache =  super.getMissingCache(name);
        if(StringUtils.isEmpty(name)){
            return redisCache;
        }

        return super.createRedisCache(name,RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(Integer.parseInt(name))));

    }
}
