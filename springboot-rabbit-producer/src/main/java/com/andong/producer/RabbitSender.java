package com.andong.producer;

import com.andong.entity.Merchant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author andong
 * @Date: 2021/04/03/14:43
 * @Description:
 */
@Component
@PropertySource({"classpath:and-rabbitmq-config.properties"})
public class RabbitSender {

    @Value("${com.andong.directexchange}")
    private String directExchange;

    @Value("${com.andong.topicexchange}")
    private String topicExchange;

    @Value("${com.andong.fanoutexchange}")
    private String fanoutExchange;

    @Value("${com.andong.directroutingkey}")
    private String directRoutingKey;

    @Value("${com.andong.topicroutingkey1}")
    private String topicRoutingKey1;

    @Value("${com.andong.topicroutingkey2}")
    private String topicRoutingKey2;


    // 自定义的模板，所有的消息都会转换成JSON发送
    @Autowired
    AmqpTemplate gupaoTemplate;

    public void send() throws JsonProcessingException {
        Merchant merchant =  new Merchant("1212121","a direct msg : test");
        gupaoTemplate.convertAndSend(directExchange,directRoutingKey, merchant);

        gupaoTemplate.convertAndSend(topicExchange,topicRoutingKey1, "a topic msg : shanghai.gupao.teacher");
        gupaoTemplate.convertAndSend(topicExchange,topicRoutingKey2, "a topic msg : changsha.gupao.student");

        // 发送JSON字符串
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(merchant);
        System.out.println(json);
        gupaoTemplate.convertAndSend(fanoutExchange,"", json);
    }

}
