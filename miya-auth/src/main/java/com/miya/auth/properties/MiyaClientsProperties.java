package com.miya.auth.properties;

import lombok.Data;

/**
 * @author Caixiaowei
 * @ClassName MiyaClientsProperties.java
 * @Description client 配置类
 * @createTime 2020年05月12日 16:59:00
 */
@Data
public class MiyaClientsProperties {

    private String client;
    private String secret;
    private String grantType = "password,authorization_code,refresh_token";
    private String scope = "all";
}
