package com.qhbd.config;

import com.qhbd.config.redis.LocalRedisCacheManager;
import com.qhbd.config.redis.RedisExpireCacheManager;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.jcache.JCacheManagerFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import javax.cache.CacheManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class CacheConfigure {


    /**
     * 自带的缓存实现
     * @return
     */
    @Bean("simpleCacheManager")
    @Primary
    public SimpleCacheManager getSimpleCacheManager(){
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        List< Cache> list = new ArrayList<>();
        list.add(new ConcurrentMapCache("default"));
        simpleCacheManager.setCaches(list);
        return simpleCacheManager;
    }

    /**
     * redis缓存 支持设置key的过期时间
     * @return
     */
    @Bean("redisCacheManager")
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        RedisCacheConfiguration defaultCacheConfig =
                RedisCacheConfiguration.defaultCacheConfig()
                        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                        .entryTtl(Duration.ofSeconds(30))
                        .disableKeyPrefix();

        //初始化RedisCacheManager
        RedisCacheManager cacheManager = new RedisExpireCacheManager(redisCacheWriter, defaultCacheConfig);
        return cacheManager;
    }


    /**
     * ehcache 3.0 实现 缓存可用@Cacheable 注解 也可用 @CacheResult注解
     *  @Cacheable 是spring的缓存框架注解
     *  @CacheResult 是javax.cache 的缓存框架注解
     * @param cacheManager
     * @return
     * @throws URISyntaxException
     */
    @Bean("jCacheCacheManager")
    public JCacheCacheManager createJCacheCacheManager(CacheManager cacheManager) throws URISyntaxException {
        JCacheCacheManager jCacheCacheManager = new JCacheCacheManager();
        jCacheCacheManager.setCacheManager(cacheManager);
        return  jCacheCacheManager;
    }

    @Bean
    public JCacheManagerFactoryBean createJCacheManagerFactoryBean() throws URISyntaxException {
        JCacheManagerFactoryBean jCacheManagerFactoryBean = new JCacheManagerFactoryBean();
        jCacheManagerFactoryBean.setCacheManagerUri(new URI("file:/C:/my/idea/yunji/dubbo/user-order-base/src/main/resources/ehcache.xml") );
        return jCacheManagerFactoryBean;
    }


    /**
     * ehcache作为一级缓存  redis 作为二级缓存
     * @return
     */
    @Bean("ehAndRedisCacheManager")
    public RedisCacheManager createEhAndRedisCacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        RedisCacheConfiguration defaultCacheConfig =
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofSeconds(30));
        //初始化RedisCacheManager
        RedisCacheManager cacheManager = new LocalRedisCacheManager(redisCacheWriter, defaultCacheConfig);
        return cacheManager;
    }

}
