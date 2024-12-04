package com.sample.demo.api.redis.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@RedisHash(value = "adminCode")
public class AdminCode {

    @Id
    private String code;
}
