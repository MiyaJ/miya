package com.miya.auth;

import com.miya.annotation.EnableMiyaAuthExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Caixiaowei-zy
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableMiyaAuthExceptionHandler
public class MiyaAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiyaAuthApplication.class, args);
    }

}
