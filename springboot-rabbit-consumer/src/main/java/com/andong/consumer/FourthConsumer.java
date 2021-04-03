package com.andong.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author andong
 * @Date: 2021/04/03/14:00
 * @Description:
 */
@Component
@PropertySource({"classpath:and-rabbitmq-config.properties"})
@RabbitListener(queues = "${com.andong.fourthqueue}",containerFactory="rabbitListenerContainerFactory")
public class FourthConsumer {

    @RabbitHandler
    public void process(String msg){
        System.out.println("Fourth Queue received msg : " + msg);
    }
}
