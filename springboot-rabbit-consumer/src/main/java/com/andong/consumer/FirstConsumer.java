package com.andong.consumer;

import com.andong.entity.Merchant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @Author andong
 * @Date: 2021/04/03/14:00
 * @Description:
 */
@Component
@PropertySource({"classpath:and-rabbitmq-config.properties"})
@RabbitListener(queues = "${com.andong.firstqueue}",containerFactory="rabbitListenerContainerFactory")
public class FirstConsumer {

    @RabbitHandler
    public void process(@Payload Merchant merchant){
        System.out.println("First Queue received msg : " + merchant.getName());
    }
}
