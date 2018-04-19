package com.dragon.run;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述:启动提供方服务
 *
 * @author yanpenglei
 * @create 2017-10-27 11:49
 **/
@SpringBootApplication
@MapperScan("com.dragon.mapper")
@ComponentScan(value = {"com.dragon"})
public class PmsService {

    public static void main(String[] args) {
        SpringApplication.run(PmsService.class, args);
    }
}
