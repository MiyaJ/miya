package com.miya.warehouse;

import com.miya.annotation.EnableMiyaLettuceRedis;
import com.miya.annotation.MiyaCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Caixiaowei-zy
 */
@SpringBootApplication
@EnableFeignClients
@EnableMiyaLettuceRedis
@MiyaCloudApplication
public class MiyaWarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiyaWarehouseApplication.class, args);
    }

}
