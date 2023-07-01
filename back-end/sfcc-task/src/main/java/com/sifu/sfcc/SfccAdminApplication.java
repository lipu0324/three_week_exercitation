package com.sifu.sfcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 应用启动入口
 * Created by commerce on 2018/4/26.
 */
@SpringBootApplication(scanBasePackages = "com.sifu.sfcc")
@EnableScheduling
public class SfccAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SfccAdminApplication.class, args);
        
    }
}
