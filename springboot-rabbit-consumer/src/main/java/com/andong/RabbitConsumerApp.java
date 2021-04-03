package com.andong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author andong
 * @Date: 2021/04/03/14:14
 * @Description:
 */
@SpringBootApplication
public class RabbitConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(RabbitConsumerApp.class,args);
    }
}
