package com.sifu.sfcc.search.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * Created by qingmeng on 2020/10/24.
 */
@Configuration
@MapperScan({"com.sifu.sfcc.mapper","com.sifu.sfcc.search.dao"})
public class MyBatisConfig {
}
