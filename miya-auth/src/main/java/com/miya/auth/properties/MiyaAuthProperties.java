package com.miya.auth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Caixiaowei
 * @ClassName MiyaAuthProperties.java
 * @Description TODO
 * @createTime 2020年05月12日 17:00:00
 */
@Data
@SpringBootConfiguration
@PropertySource(value = "classpath:miya-auth.properties")
@ConfigurationProperties(prefix = "miya.auth")
public class MiyaAuthProperties {

    private MiyaClientsProperties[] clients = {};
    private int accessTokenValiditySeconds = 60 * 60 * 24;
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;
}
