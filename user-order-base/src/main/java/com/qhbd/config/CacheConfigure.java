package com.qhbd.config;

import com.qhbd.config.redis.MyRedisCacheManager;
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

    public SimpleCacheManager getSimpleCacheManager(){
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        List< Cache> list = new ArrayList<>();
        list.add(new ConcurrentMapCache("default"));
        simpleCacheManager.setCaches(list);
        return simpleCacheManager;
    }

    /**
     * redis缓存
     * @return
     */
    @Bean("redisCacheManager")
    @Primary

    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        //设置默认超过期时间是30秒
        defaultCacheConfig = defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
        //初始化RedisCacheManager
        RedisCacheManager cacheManager = new MyRedisCacheManager(redisCacheWriter, defaultCacheConfig);
        return cacheManager;
    }







    /**
     * ehcache 2.0 实现
     * @param cacheManager
     * @return
     * @throws URISyntaxException
     */
    /*
    @Bean(name = "appEhCacheCacheManager")
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
        return new EhCacheCacheManager (bean.getObject ());
    }


    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean ();
        cacheManagerFactoryBean.setConfigLocation (new ClassPathResource("conf/ehcache-app.xml"));
        cacheManagerFactoryBean.setShared (true);
        return cacheManagerFactoryBean;
    }*/




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
        jCacheManagerFactoryBean.setCacheManagerUri(new URI("file:/D:/idea/project/dubbo/user-order-base/src/main/resources/ehcache.xml") );
        return jCacheManagerFactoryBean;
    }

}
