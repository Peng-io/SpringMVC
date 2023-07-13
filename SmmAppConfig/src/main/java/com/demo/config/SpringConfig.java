package com.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan({"com.demo.Service"})
@Import({jdbcConfig.class, MybatisConfig.class})
public class SpringConfig {
// spring 管理 Mybatis, Service业务层
}
