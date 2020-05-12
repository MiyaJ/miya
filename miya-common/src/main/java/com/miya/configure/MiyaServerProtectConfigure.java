package com.miya.configure;

import com.miya.interceptor.MiyaServerProtectInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Caixiaowei
 * @ClassName MiyaServerProtectConfigure.java
 * @Description 服务保护配置, 将过滤器注册到ioc 中
 * @createTime 2020年05月12日 19:06:00
 */
@Slf4j
public class MiyaServerProtectConfigure implements WebMvcConfigurer {

    /**
     * @title 注册拦截器 MiyaServerProtectInterceptor
     * @description
     * @author Caixiaowei
     * @updateTime 2020/5/12 19:10
     * @throws
     */
    @Bean
    public HandlerInterceptor miyaServerProtectInterceptor() {
        return new MiyaServerProtectInterceptor();
    }

    /**
     * @title 添加拦截器到 链
     * @description
     * @author Caixiaowei
     * @updateTime 2020/5/12 19:10
     * @throws
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(miyaServerProtectInterceptor());
    }
}
