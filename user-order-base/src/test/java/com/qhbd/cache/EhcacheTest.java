package com.qhbd.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.UserManagedCache;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.ResourcePools;
import org.ehcache.config.builders.*;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Expirations;
import org.ehcache.xml.model.TimeUnit;
import org.junit.Test;
import java.io.File;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class EhcacheTest {

    /**
     *  key - value 堆 堆外 磁盘存储
     */
    @Test
    public void baseHeapDemo(){
        ResourcePools resourcePools = ResourcePoolsBuilder.newResourcePoolsBuilder()
                .heap(10,EntryUnit.ENTRIES) // 堆内存 map
                .offheap(10, MemoryUnit.MB) // 堆外内存  nio 的buffer
                .disk(20, MemoryUnit.MB, false) // true 表示CacheManager关闭的时候 不会清楚数据文件
                .build();
        CacheConfiguration<String,StringBuilder> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, StringBuilder.class,resourcePools)
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(20)))
                .build();

        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache("my",cacheConfiguration)
                .with(CacheManagerBuilder.persistence("D:\\" + File.separator + "myData"))
                .withDefaultSizeOfMaxObjectSize(500, MemoryUnit.B)
                .withDefaultSizeOfMaxObjectGraph(2000)
                .build(true);

        Cache<String, StringBuilder> myCache = cacheManager.getCache("my",String.class,StringBuilder.class);

        for (int i=0;i<100;i++){
            myCache.put("phone"+i, new StringBuilder("000"+i));
        }
        for (int i=0;i<100;i++){
            if(myCache.get("phone"+i) != null){
                System.out.println("phone"+i +"  "+myCache.get("phone"+i));
            }

        }

        cacheManager.close();
    }



    /**
     * 用户管理缓存demo,不被 CacheManager管理
     */
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
}
