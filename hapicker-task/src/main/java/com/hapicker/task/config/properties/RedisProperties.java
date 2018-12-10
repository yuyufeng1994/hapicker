package com.hapicker.task.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author yuyufeng
 */
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    @Value("${redis.database}")
    private int database;
    /**
     * Redis服务器地址
     */
    @Value("${redis.host}")
    private String host;
    /**
     * Redis服务器连接端口
     */
    @Value("${redis.port}")
    private int port;
    /**
     * Redis服务器连接密码（默认为空）
     */
    @Value("${redis.password}")
    private String password;
    /**
     * 连接池最大连接数（使用负值表示没有限制）
     */
    @Value("${redis.maxTotal}")
    private int maxTotal;
    /**
     * 连接池最大阻塞等待时间（使用负值表示没有限制）
     */
    @Value("${redis.maxWaitMillis}")
    private int maxWaitMillis;
    /**
     * 连接池中的最大空闲连接
     */
    @Value("${redis.maxIdle}")
    private int maxIdle;
    /**
     * 连接池中的最小空闲连接
     */
    @Value("${redis.minIdle}")
    private int minIdle;
    /**
     * 连接超时时间（毫秒）
     */
    @Value("${redis.timeout}")
    private int timeout;

    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${redis.testOnReturn}")
    private boolean testOnReturn;


    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }
}
