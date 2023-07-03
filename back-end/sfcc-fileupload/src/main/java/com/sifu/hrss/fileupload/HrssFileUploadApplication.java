package com.sifu.hrss.fileupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class HrssFileUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(HrssFileUploadApplication.class, args);
    }
}
