package com.woniu.intnet.timer;

import com.woniu.service.impl.ClassCurrencyMsgServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQSendMsgTimmer {

    private final ClassCurrencyMsgServiceImpl classCurrencyMsgService;


    @SneakyThrows
    @Scheduled(cron = "0 30 * * * *")
    protected void sendMsgToQueue(){
        classCurrencyMsgService.timedUpperAndLowerShelves();
    }
}
