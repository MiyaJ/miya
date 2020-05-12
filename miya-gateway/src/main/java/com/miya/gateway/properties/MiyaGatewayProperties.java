package com.miya.gateway.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Caixiaowei
 * @ClassName MiyaGatewayProperties.java
 * @Description TODO
 * @createTime 2020年05月12日 15:05:00
 */
@Data
@SpringBootConfiguration
@PropertySource("classpath:miya-gateway.properties")
@ConfigurationProperties("miya.gateway")
public class MiyaGatewayProperties {

    /**
     * 禁止外部访问的 URI，多个值的话以逗号分隔
     */
    private String forbidRequestUri;
}
