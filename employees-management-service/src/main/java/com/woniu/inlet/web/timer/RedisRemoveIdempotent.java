package com.woniu.inlet.web.timer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.Callable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RedisRemoveIdempotent implements Callable {

    private String name;
    private String token;
    private RedisTemplate redisTemplate;

    @Override
    public Object call() throws Exception {
        return redisTemplate.opsForSet().remove(name, token);
    }
}
