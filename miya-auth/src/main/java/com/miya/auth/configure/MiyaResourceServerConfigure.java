package com.miya.auth.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author Caixiaowei
 * @ClassName MiyaResourceServerConfigure.java
 * @Description 资源服务器配置
 * @createTime 2020年05月12日 13:11:00
 */
@Configuration
@EnableResourceServer
public class MiyaResourceServerConfigure extends ResourceServerConfigurerAdapter {

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
}
