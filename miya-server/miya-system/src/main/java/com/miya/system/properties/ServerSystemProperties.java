package com.miya.system.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Caixiaowei
 * @ClassName ServerSystemProperties.java
 * @Description TODO
 * @createTime 2020年05月14日 14:32:00
 */
@Data
@SpringBootConfiguration
@PropertySource(value = "classpath:miya-system.properties")
@ConfigurationProperties(prefix = "miya-system")
public class ServerSystemProperties {

    /**
     * 免认证 URI，多个值的话以逗号分隔
     */
    private String anonUrl;

    /**
     * swagger 在线文档配置
     */
    private SwaggerProperties swagger = new SwaggerProperties();
}
