package com.miya.system.configure;

import com.miya.system.properties.ServerSystemProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author Caixiaowei
 * @ClassName MiyaServerSystemResourceServerConfigure.java
 * @Description 资源服务器的配置
 * @createTime 2020年05月12日 16:42:00
 */
@Configuration
@EnableResourceServer
public class MiyaServerSystemResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private ServerSystemProperties properties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");

        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers("/**").authenticated();
    }
}
