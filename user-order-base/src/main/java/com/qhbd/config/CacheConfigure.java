package com.qhbd.config;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.jcache.JCacheManagerFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.cache.CacheManager;
import java.net.URI;
import java.net.URISyntaxException;
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
        jCacheManagerFactoryBean.setCacheManagerUri(new URI("file:/C:/my/idea/yunji/dubbo/user-order-base/target/classes/ehcache.xml") );
        return jCacheManagerFactoryBean;
    }

}
