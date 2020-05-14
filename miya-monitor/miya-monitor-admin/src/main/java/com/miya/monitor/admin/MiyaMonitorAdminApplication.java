package com.miya.monitor.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Caixiaowei-zy
 */
@SpringBootApplication
@EnableAdminServer
public class MiyaMonitorAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiyaMonitorAdminApplication.class, args);
    }

}
