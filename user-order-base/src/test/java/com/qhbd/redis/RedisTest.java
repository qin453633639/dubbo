package com.qhbd.redis;

import com.qhbd.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by qinwei on 2019/5/19.
 */
public class RedisTest extends BaseTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testPut(){
        redisTemplate.opsForValue().set("name","sds",10, TimeUnit.SECONDS);
    }
}
