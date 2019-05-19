package com.qhbd.config.redis;


import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * Created by qinwei on 2019/5/19.
 */
@Configuration
public class RedisConfigure {


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 全局开启AutoType，不建议使用
        // ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        // 建议使用这种方式，小范围指定白名单
        ParserConfig.getGlobalInstance().addAccept("com.xiaolyuh.");
        // 设置值（value）的序列化采用FastJsonRedisSerializer。
        redisTemplate.setValueSerializer(new FastJson2JsonRedisSerializer<Object>(Object.class));
        redisTemplate.setHashValueSerializer(new FastJson2JsonRedisSerializer<Object>(Object.class));
        // 设置键（key）的序列化采用StringRedisSerializer。
        redisTemplate.setKeySerializer(new FastJson2JsonRedisSerializer<Object>(Object.class));
        redisTemplate.setHashKeySerializer(new FastJson2JsonRedisSerializer<Object>(Object.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}


