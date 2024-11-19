package com.sample.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisUtil(final RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Object getSessionData(String sessionId) { //TODO: Enum 처리
        String key = String.format("spring:session:sessions:%s", sessionId);

        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        log.debug(key);
        log.debug((String) hashOperations.get(key, "sessionAttr:auth"));

        return hashOperations.get(key, "sessionAttr:auth");
    }

}
