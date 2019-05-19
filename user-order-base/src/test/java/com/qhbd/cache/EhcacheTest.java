package com.qhbd.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.UserManagedCache;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.Configuration;
import org.ehcache.config.ResourcePools;
import org.ehcache.config.builders.*;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.xml.XmlConfiguration;
import org.junit.Test;

import javax.management.MBeanServer;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.time.Duration;

public class EhcacheTest {


    /**
     * xml 配置
     */
    @Test
    public void xmlDemo(){
        URL myUrl = getClass().getResource("/ehcache.xml");
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        CacheManager myCacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        myCacheManager.init();
        Cache<String,String> cache = myCacheManager.getCache("product", String.class, String.class);
        cache.put("name","smartteam");
        System.out.println(cache.get("name"));
        myCacheManager.close();
    }

    /**
     * 集群配置
     */
    @Test
    @Deprecated
    public void clusterDemo(){
        /*CacheManagerBuilder<PersistentCacheManager> clusteredCacheManagerBuilder =
                CacheManagerBuilder.newCacheManagerBuilder()
                        .with(ClusteringServiceConfigurationBuilder.cluster(URI.create("terracotta://localhost/my-application"))
                                .autoCreate());
        PersistentCacheManager cacheManager = clusteredCacheManagerBuilder.build(true);

        cacheManager.close();*/
    }



    /**
     * 用户管理缓存demo,不被 CacheManager管理
     */
    @Deprecated
    @Test
    public void userManagedCacheDemo(){
        UserManagedCache<Long, String> userManagedCache =
                UserManagedCacheBuilder.newUserManagedCacheBuilder(Long.class, String.class)
                        .build(false);
        userManagedCache.init();
        userManagedCache.put(1L, "da one!");
        System.out.println(userManagedCache.get(1L));
        userManagedCache.close();
    }


    /**
     *  api配置使用
     *  key - value 堆 堆外 磁盘存储
     */
    @Test
    public void baseDemo() throws InterruptedException {
        ResourcePools resourcePools = ResourcePoolsBuilder.newResourcePoolsBuilder()
                .heap(10,EntryUnit.ENTRIES) // 堆内存 map
                .offheap(10, MemoryUnit.MB) // 堆外内存  nio 的buffer
                .disk(20, MemoryUnit.MB, false) // true  (表示CacheManager关闭的时候 不会清楚数据文件 但manager未关闭，也不能持久化)
                .build();
        CacheConfiguration<String,StringBuilder> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, StringBuilder.class,resourcePools)
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(5)))
                .build();
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache("my",cacheConfiguration)
                .with(CacheManagerBuilder.persistence("D:\\" + File.separator + "myData"))
                .withDefaultSizeOfMaxObjectSize(500, MemoryUnit.B)
                .withDefaultSizeOfMaxObjectGraph(2000)
                .build(true);

        Cache<String, StringBuilder> myCache = cacheManager.getCache("my",String.class,StringBuilder.class);
        myCache.put("name",new StringBuilder("smartteam"));
        System.out.println(myCache.get("name"));
        cacheManager.close();


    }


    /**
     *  api配置使用
     *  key - value 堆 堆外 磁盘存储
     */
    @Test
    public void baseCopyDemo() throws InterruptedException {
        ResourcePools resourcePools = ResourcePoolsBuilder.newResourcePoolsBuilder()
                .heap(10,EntryUnit.ENTRIES) // 堆内存 map
                .build();
        CacheConfiguration<StringBuilder,StringBuilder> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(StringBuilder.class, StringBuilder.class,resourcePools)
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(5)))
                .withKeySerializingCopier()
                .withValueSerializingCopier()
                .build();

        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache("my",cacheConfiguration)
                .build(true);

        Cache<StringBuilder, StringBuilder> myCache = cacheManager.getCache("my",StringBuilder.class,StringBuilder.class);

        StringBuilder key = new StringBuilder("key");
        StringBuilder value = new StringBuilder("value");
        System.out.println("put "+key +"  "+value);
        myCache.put(key,value);
        System.out.println("get "+myCache.get(key));



        cacheManager.close();
    }




}
