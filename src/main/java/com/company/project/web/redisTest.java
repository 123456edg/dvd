package com.company.project.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class redisTest {
    
//    @Resource
//    private RedisTemplate<String, Object> redisTemplate;
    
    @Test
    public void test1(){
        //连接本地的 Redis 服务
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        jedis.auth("123456");
//        System.out.println("连接成功");
//        ValueOperations<String,Object> vo = redisTemplate.opsForValue();
//        vo.set("key","value");
//        System.out.println(vo.get("myKey"));
        System.out.println("111");
    
    }
}
