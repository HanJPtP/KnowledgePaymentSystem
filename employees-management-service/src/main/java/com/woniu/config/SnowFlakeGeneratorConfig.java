package com.woniu.config;

import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SnowFlakeGeneratorConfig {

    @Bean
    public SnowFlakeGenerator getSnowFlakeGenerator() {
        return new SnowFlakeGenerator(10, 15);
    }

}
