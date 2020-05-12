package com.miya.system;

import com.miya.annotation.EnableMiyaAuthExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author Caixiaowei-zy
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableMiyaAuthExceptionHandler
public class MiyaSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiyaSystemApplication.class, args);
    }

}
