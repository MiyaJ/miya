package com.miya.auth.configure;

import com.miya.auth.properties.MiyaAuthProperties;
import com.miya.auth.properties.MiyaClientsProperties;
import com.miya.auth.service.MiyaUserDetailService;
import com.miya.auth.translator.MiyaWebResponseExceptionTranslator;
import com.miya.exception.MiyaAuthException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.util.CollectionUtils;

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
    @Autowired
    private MiyaAuthProperties authProperties;
    @Autowired
    private MiyaWebResponseExceptionTranslator exceptionTranslator;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        MiyaClientsProperties[] clientsArray = authProperties.getClients();
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if (ArrayUtils.isNotEmpty(clientsArray)) {
            for (MiyaClientsProperties client : clientsArray) {
                if (StringUtils.isBlank(client.getClient())) {
                    throw new MiyaAuthException("client 不能为空");
                }
                if (StringUtils.isBlank(client.getSecret())) {
                    throw new MiyaAuthException("secret不能为空");
                }
                String[] grantTypes = StringUtils.splitByWholeSeparatorPreserveAllTokens(client.getGrantType(), ",");
                builder.withClient(client.getClient())
                        .secret(passwordEncoder.encode(client.getSecret()))
                        .authorizedGrantTypes(grantTypes)
                        .scopes(client.getScope());
            }
        }
    }

    @Override
    @SuppressWarnings("all")
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager)
                .tokenServices(defaultTokenServices())
                .exceptionTranslator(exceptionTranslator);
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
        tokenServices.setAccessTokenValiditySeconds(authProperties.getAccessTokenValiditySeconds());
        // token刷新时间
        tokenServices.setRefreshTokenValiditySeconds(authProperties.getRefreshTokenValiditySeconds());
        return tokenServices;
    }
}
