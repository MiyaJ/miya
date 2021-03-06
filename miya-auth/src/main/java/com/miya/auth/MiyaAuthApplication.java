package com.miya.auth;

import com.miya.annotation.EnableMiyaLettuceRedis;
import com.miya.annotation.MiyaCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Caixiaowei-zy
 */
@SpringBootApplication
@MapperScan("com.miya.auth.mapper")
@MiyaCloudApplication
@EnableMiyaLettuceRedis
public class MiyaAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiyaAuthApplication.class, args);
    }

}
