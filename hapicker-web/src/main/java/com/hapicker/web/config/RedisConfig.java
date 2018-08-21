package com.hapicker.web.config;


import com.hapicker.web.config.properties.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;
/**
 * @author yuyufeng
 * @date 2018年8月21日17:25:57
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private RedisProperties properties;


    @Bean
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    public RedisConnectionFactory connectionFactory() {
        JedisConnectionFactory jedis = new JedisConnectionFactory();
        jedis.setHostName(properties.getHost());
        jedis.setPort(properties.getPort());
        jedis.setPassword(properties.getPassword());
        jedis.setDatabase(properties.getDatabase());

        jedis.setPoolConfig(poolCofig());
        // 初始化连接pool
        jedis.afterPropertiesSet();
        return jedis;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager manager = new RedisCacheManager(redisTemplate);
        return manager;
    }
    @Bean
    public JedisPoolConfig poolCofig() {
        JedisPoolConfig poolCofig = new JedisPoolConfig();
        poolCofig.setMaxIdle(properties.getMaxIdle());
        poolCofig.setMaxTotal(properties.getMaxTotal());
        poolCofig.setMaxWaitMillis(properties.getMaxWaitMillis());
        poolCofig.setTestOnBorrow(properties.isTestOnBorrow());
        poolCofig.setTestOnReturn(properties.isTestOnReturn());
        return poolCofig;
    }

}