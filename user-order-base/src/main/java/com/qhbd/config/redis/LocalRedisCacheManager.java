package com.qhbd.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.time.Duration;


public class LocalRedisCacheManager extends RedisCacheManager {

    @Autowired
    private JCacheCacheManager jCacheCacheManager ;
    private RedisSerializationContext.SerializationPair  key =RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());

    public LocalRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration, true);
    }

    @Override
    protected RedisCache getMissingCache(String name) {
        if(StringUtils.isEmpty(name)){
            return  super.getMissingCache(name);
        }
        Cache local = jCacheCacheManager.getCache(name);
        return new LoalRedisCache(local, super.createRedisCache(name,RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(key)
                .entryTtl(Duration.ofSeconds(30))
                .disableKeyPrefix()));


    }
}
