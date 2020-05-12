package com.miya.annotation;

import com.miya.configure.MiyaOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author caixiaowei
 * @version 1.0
 * @classname EnableMiyaOauth2FeignClient
 * @description 开启带令牌的Feign请求，避免微服务内部调用出现401异常；
 * @date 2020/05/12 23:48
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MiyaOAuth2FeignConfigure.class)
public @interface EnableMiyaOauth2FeignClient {
}
