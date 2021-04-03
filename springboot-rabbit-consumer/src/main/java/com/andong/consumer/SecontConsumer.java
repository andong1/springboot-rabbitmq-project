package com.andong.consumer;

import com.andong.entity.Merchant;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author andong
 * @Date: 2021/04/03/14:00
 * @Description:
 */
@Component
@PropertySource({"classpath:and-rabbitmq-config.properties"})
@RabbitListener(queues = "${com.andong.secondqueue}",containerFactory="rabbitListenerContainerFactory")
public class SecontConsumer {

    @RabbitHandler
    public void process(String msgContent, Channel channel, Message message) throws IOException {
        System.out.println("First Queue received msg : " + msgContent);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
