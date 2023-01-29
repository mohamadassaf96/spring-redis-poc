package com.example.boilerplate.config;

import com.example.boilerplate.service.impl.RedisService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class})
@ComponentScan(basePackages = {"com.example.boilerplate.config",
        "com.example.boilerplate.util",
        "com.example.boilerplate.filters",
        "com.example.boilerplate.service.impl"})
public class MockRepositories {
    @MockBean
    private RedisTemplate<Object, Object> redisTemplate;
    @MockBean
    private RedisService redisService;
}
