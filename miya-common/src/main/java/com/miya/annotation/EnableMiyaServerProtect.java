package com.miya.annotation;

import com.miya.configure.MiyaServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Caixiaowei
 * @ClassName EnableMiyaServerProtect.java
 * @Description 驱动 MiyaServerProtectConfigure 配置,开启微服务防护，避免客户端绕过网关直接请求微服务
 * @createTime 2020年05月12日 19:14:00
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MiyaServerProtectConfigure.class)
public @interface EnableMiyaServerProtect {
}
