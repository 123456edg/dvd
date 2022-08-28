package com.company.project.configurer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableAutoConfiguration
@ConfigurationProperties(prefix="spring.redis")
public class RedisConfigurer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    //    Redis服务器（IP）
    @Value("${spring.redis.host}")
    private String host;
//    Redis服务端口号
    @Value("${spring.redis.port}")
    private int port;
//    Redis服务器连接超时的时间（0代表不超时）
    @Value("${spring.redis.timeout}")
    private int timeout;
//    控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
//    待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
//    @ConfigurationProperties(prefix="spring.redis")
    public JedisPoolConfig getRedisConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        return config;
    }

    @Bean
//    @ConfigurationProperties(prefix="spring.redis")
    public JedisConnectionFactory getConnectionFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        JedisPoolConfig config = getRedisConfig();
        factory.setPoolConfig(config);
        factory.setHostName(host);
        factory.setPassword(password);
        factory.setTimeout(timeout);
        logger.info("JedisConnectionFactory bean init success.");
        return factory;
    }

    @Bean
    public JedisPool getJedisPool() {
        logger.info("JedisPool注入成功！！");
        logger.info("redis地址：" + host + ":" + port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        return new JedisPool(jedisPoolConfig, host, port, timeout, password);
    }
    @Bean
    public RedisTemplate<?, ?> getRedisTemplate(){
        return new StringRedisTemplate(getConnectionFactory());
    }
    
    @Bean
    public RedisTemplate<?, ?> redisTemplate(final RedisConnectionFactory connectionFactory) {
        final RedisTemplate<?, ?> template = new RedisTemplate<>();
        //连接工程
        template.setConnectionFactory(connectionFactory);
        
        //key采用String的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        //value序列化方式采用jackson
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        
        
        return template;
    }
    
}
