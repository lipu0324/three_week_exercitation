package com.sifu.sfcc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;

@Configuration //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 2.开启定时任务
public class XmkScheduleConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmkScheduleConfig.class);

    //3.添加定时任务(每七天定时执行一次)
    @Scheduled(cron = "0 1 */7 * * ?")
    private void configureTasks() {
        LOGGER.info("----configureTasks----" );


    }


}