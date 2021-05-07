package com.miya.member.configure;

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
 * @ClassName MiyaServerMemberResourceServerConfigure.java
 * @Description 资源服务器的配置
 * @createTime 2020年05月12日 16:42:00
 */
@Configuration
@EnableResourceServer
public class MiyaServerMemberResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private MiyaAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private MiyaAuthExceptionEntryPoint exceptionEntryPoint;

    private String anonUrl = "/swagger-ui.html,/webjars/**,/swagger-resources/**,/v2/api-docs/**,/,/csrf,/test/**,/actuator/**";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(anonUrl, ",");
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
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
