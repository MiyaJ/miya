package com.miya.auth.configure;

import com.miya.auth.properties.MiyaAuthProperties;
import com.miya.handler.MiyaAccessDeniedHandler;
import com.miya.handler.MiyaAuthExceptionEntryPoint;
import org.apache.commons.lang3.StringUtils;
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
 *              用于资源保护，客户端只能通过OAuth2 协议发放的令牌来从资源服务器中获取受保护的资源
 * @createTime 2020年05月12日 13:11:00
 */
@Configuration
@EnableResourceServer
public class MiyaResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private MiyaAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private MiyaAuthExceptionEntryPoint exceptionEntryPoint;
    @Autowired
    private MiyaAuthProperties properties;

    /**
     * @title 配置: 对所有请求有效
     * @description 
     * @author Caixiaowei 
     * @updateTime 2020/5/12 13:13 
     * @throws
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll() // 配置免认证
                .antMatchers("/**").authenticated()
                .and().httpBasic();
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
