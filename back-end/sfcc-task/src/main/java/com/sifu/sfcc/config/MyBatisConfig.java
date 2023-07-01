package com.sifu.sfcc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis配置类
 * Created by cc on 2019/4/8.
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.sifu.sfcc.mapper","com.sifu.sfcc.dao"})
public class MyBatisConfig {
}
