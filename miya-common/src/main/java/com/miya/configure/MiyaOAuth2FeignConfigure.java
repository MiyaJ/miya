package com.miya.configure;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * @author caixiaowei
 * @version 1.0
 * @classname MiyaOAuth2FeignConfigure
 * @description feign 授权配置
 *              拦截Feign 请求，在请求头上补充原request的请求头信息
 * @date 2020/05/12 23:45
 */
public class MiyaOAuth2FeignConfigure {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            if (details instanceof OAuth2AuthenticationDetails) {
                String authorizationToken = ((OAuth2AuthenticationDetails) details).getTokenValue();
                requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + authorizationToken);
            }
        };
    }
}
