package com.qhbd.config.redis;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.time.Duration;

public class MultiCacheManager extends RedisCacheManager {

    private RedisSerializationContext.SerializationPair  key =RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());

    public MultiCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration, true);
    }

    @Override
    protected RedisCache getMissingCache(String name) {
        RedisCache redisCache =  super.getMissingCache(name);
        if(StringUtils.isEmpty(name)){
            return redisCache;
        }
        return super.createRedisCache(name,RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(key)
                .disableKeyPrefix()
                .entryTtl(Duration.ofSeconds(Integer.parseInt(name))));

    }
}
