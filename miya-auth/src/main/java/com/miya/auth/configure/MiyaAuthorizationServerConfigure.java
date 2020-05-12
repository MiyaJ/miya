package com.miya.auth.configure;

import com.miya.auth.service.MiyaUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author Caixiaowei
 * @ClassName MiyaAuthorizationServerConfigure.java
 * @Description 授权认证的安全配置类
 * @createTime 2020年05月12日 13:13:00
 */
@Configuration
@EnableAuthorizationServer
public class MiyaAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private MiyaUserDetailService userDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 客户端获取token的配置
                .withClient("miya")
                .secret(passwordEncoder.encode("123456"))
                .authorizedGrantTypes("password", "refresh_token")
                // token 范围
                .scopes("all");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager)
                .tokenServices(defaultTokenServices());
    }

    /**
     * @title token策略: redis 认证服务器生成的令牌将被存储到Redis
     * @description 
     * @author Caixiaowei 
     * @updateTime 2020/5/12 13:18 
     * @throws
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * @title token 策略
     * @description 
     * @author Caixiaowei 
     * @updateTime 2020/5/12 13:16 
     * @throws
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        // 指定token的基本配置为 默认
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        // true 表示开启刷新token
        tokenServices.setSupportRefreshToken(true);
        // token 有效期
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 24);
        // token刷新时间
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
    }
}
