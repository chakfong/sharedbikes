package com.fly.common.configuration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
//
//@Configuration
//@EnableConfigurationProperties(RedisProperties.class)
//public class RedissonConfig {
//    @Value("${spring.redis.redisson.pool-size:64}")
//    private int poolSize = 64;
//
//    @Bean
//    @ConditionalOnMissingBean(RedissonClient.class)
//    public RedissonClient redisson(RedisProperties properties) throws IOException{
//        Config config = new Config();
//
//        // 单机模式
//        config.useSingleServer()
//                .setAddress("redis://" + properties.getHost() + ":" + properties.getPort())
//                .setTimeout(properties.getTimeout() == 0 ? 3000 : properties.getTimeout())
//                .setPassword(properties.getPassword())
//                .setDatabase(properties.getDatabase())
//                .setConnectionPoolSize(poolSize);
//        return Redisson.create(config.setCodec(new JsonJacksonCodec()));
//    }
//}
