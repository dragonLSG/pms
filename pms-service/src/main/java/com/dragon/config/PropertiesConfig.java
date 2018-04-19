package com.dragon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * 加载配置
 */
@Configuration
@PropertySource("classpath:dubbo.properties")
@ImportResource({"classpath:dubbo/*.xml"})
public class PropertiesConfig {
}
