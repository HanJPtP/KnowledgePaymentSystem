package com.woniu.inlet.timer.thread;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.Callable;

@Data
@AllArgsConstructor
public class RedisTaskRemoveToken implements Callable {

    private String token;
    private String setKey;
    private RedisTemplate redisTemplate;

    @Override
    public Object call() throws Exception {
        return redisTemplate.opsForSet().remove(setKey, token);
    }
}
