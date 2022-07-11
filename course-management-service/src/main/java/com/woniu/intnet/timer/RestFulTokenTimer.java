package com.woniu.intnet.timer;


import org.springframework.data.redis.core.StringRedisTemplate;


public class RestFulTokenTimer implements Runnable{

    private String tokenName;
    private String uuid;

    private StringRedisTemplate redisTemplate;

    public RestFulTokenTimer(){

    }

    public RestFulTokenTimer(String tokenName,String uuid,StringRedisTemplate redisTemplate){
        this.tokenName=tokenName;
        this.uuid=uuid;
        this.redisTemplate=redisTemplate;
    }

    @Override
    public void run() {
        redisTemplate.opsForSet().remove(tokenName, uuid);
    }
}
