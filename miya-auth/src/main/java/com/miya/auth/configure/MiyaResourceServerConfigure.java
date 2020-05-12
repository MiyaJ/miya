package com.miya.auth.configure;

import com.miya.handler.MiyaAccessDeniedHandler;
import com.miya.handler.MiyaAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author Caixiaowei
 * @ClassName MiyaResourceServerConfigure.java
 * @Description 资源服务器配置
 * @createTime 2020年05月12日 13:11:00
 */
@Configuration
@EnableResourceServer
public class MiyaResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private MiyaAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private MiyaAuthExceptionEntryPoint exceptionEntryPoint;

    /**
     * @title 配置: 对所有请求有效
     * @description 
     * @author Caixiaowei 
     * @updateTime 2020/5/12 13:13 
     * @throws
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();
    }

    /**
     * @title 401 403 认证异常翻译
     * @description
     * @author Caixiaowei
     * @updateTime 2020/5/12 18:10
     * @throws
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}
