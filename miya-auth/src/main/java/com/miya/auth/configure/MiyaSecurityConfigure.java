package com.miya.auth.configure;

import com.miya.auth.filter.ValidateCodeFilter;
import com.miya.auth.service.MiyaUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Caixiaowei
 * @ClassName MiyaSecurityConfigure.java
 * @Description WebSecurity 安全配置类
 *              用于处理/oauth 开头的请求， spring cloud OAuth 内部定义的过去令牌、刷新令牌等相关操作
 * @createTime 2020年05月12日 11:40:00
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Order(2)
public class MiyaSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private MiyaUserDetailService userDetailService;
    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    /**
     * @title 定义密码加密
     * @description
     * @author Caixiaowei
     * @updateTime 2020/5/12 11:44
     * @throws
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .requestMatchers()
                .antMatchers("/oauth/**")
                .antMatchers("/actuator/**")
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

//    public static void main(String[] args) {
//        String password = "123456";
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println(encoder.encode(password));
//        System.out.println(encoder.encode(password));
//    }
}
