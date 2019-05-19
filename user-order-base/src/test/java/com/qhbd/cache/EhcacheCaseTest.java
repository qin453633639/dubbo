package com.qhbd.cache;


import org.junit.Test;

import java.util.Properties;


/**
 * 缓存策略，
 * 一旦管理CacheManager关闭，则所有的缓存会被清空，硬盘上的也会清空
 */
public class EhcacheCaseTest {

    @Test
    public void aa(){
        Properties a = new Properties();
        a.put("11","1");
        System.out.println(a.toString());
    }
}
