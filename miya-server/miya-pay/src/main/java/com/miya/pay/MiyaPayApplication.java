package com.miya.pay;

import com.miya.annotation.EnableMiyaLettuceRedis;
import com.miya.annotation.MiyaCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author Caixiaowei-zy
 */
@SpringBootApplication
@EnableFeignClients
@EnableMiyaLettuceRedis
@MiyaCloudApplication
public class MiyaPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiyaPayApplication.class, args);
    }

}