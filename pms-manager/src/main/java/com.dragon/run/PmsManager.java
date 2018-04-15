package com.dragon.run;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.dragon.mapper")
@ComponentScan("com.dragon")
public class PmsManager {

    public static void main(String[] args) {
        SpringApplication.run(PmsManager.class, args);
    }
}