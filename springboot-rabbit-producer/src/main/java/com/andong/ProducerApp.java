package com.andong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author andong
 * @Date: 2021/04/03/14:46
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.andong.mapper")
public class ProducerApp {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
    }
}
