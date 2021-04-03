package com.andong.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author andong
 * @Date: 2021/04/03/13:37
 * @Description:
 */
@Configuration
@PropertySource({"classpath:and-rabbitmq-config.properties"})
public class RabbitConfig {

    @Value("${com.andong.firstqueue}")
    private String firstQueue;

    @Value("${com.andong.secondqueue}")
    private String secondQueue;

    @Value("${com.andong.thirdqueue}")
    private String thirdQueue;

    @Value("${com.andong.fourthqueue}")
    private String fourthQueue;

    @Value("${com.andong.directexchange}")
    private String directExchange;

    @Value("${com.andong.topicexchange}")
    private String topicExchange;

    @Value("${com.andong.fanoutexchange}")
    private String fanoutExchange;

    // 创建四个队列
    @Bean("firstQueue")
    public Queue getFirstQueue(){
        return new Queue(firstQueue);
    }

    @Bean("secondQueue")
    public Queue getSecondQueue(){
        return new Queue(secondQueue);
    }

    @Bean("thirdQueue")
    public Queue getThirdQueue(){
        return  new Queue(thirdQueue);
    }

    @Bean("fourthQueue")
    public Queue getFourthQueue(){
        return  new Queue(fourthQueue);
    }


    // 创建三个交换机
    @Bean("directExchange")
    public DirectExchange getDirectExchange(){
        return new DirectExchange(directExchange);
    }

    @Bean("topicExchange")
    public TopicExchange getTopicExchange(){
        return new TopicExchange(topicExchange);
    }

    @Bean("fanoutExchange")
    public FanoutExchange getFanoutExchange(){
        return new FanoutExchange(fanoutExchange);
    }


    // 定义四个绑定关系
    @Bean
    public Binding bindFirst(@Qualifier("directExchange") DirectExchange exchange,@Qualifier("firstQueue") Queue queue ){
        return BindingBuilder.bind(queue).to(exchange).with("and.first");
    }

    @Bean
    public Binding bindSecond(@Qualifier("secondQueue") Queue queue, @Qualifier("topicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("*.and.*");
    }

    @Bean
    public Binding bindThird(@Qualifier("thirdQueue") Queue queue, @Qualifier("fanoutExchange") FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding bindFourth(@Qualifier("fourthQueue") Queue queue, @Qualifier("fanoutExchange") FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }

    /**
     * 在消费端转换JSON消息
     * 监听类都要加上containerFactory属性
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setAutoStartup(true);
        return factory;
    }
}
