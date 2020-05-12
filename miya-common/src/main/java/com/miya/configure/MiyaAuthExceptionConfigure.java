package com.miya.configure;

import com.miya.handler.MiyaAccessDeniedHandler;
import com.miya.handler.MiyaAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author Caixiaowei
 * @ClassName MiyaAuthExceptionConfigure.java
 * @Description TODO
 * @createTime 2020年05月12日 18:05:00
 */
public class MiyaAuthExceptionConfigure {

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public MiyaAccessDeniedHandler accessDeniedHandler() {
        return new MiyaAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public MiyaAuthExceptionEntryPoint authenticationEntryPoint() {
        return new MiyaAuthExceptionEntryPoint();
    }
}
