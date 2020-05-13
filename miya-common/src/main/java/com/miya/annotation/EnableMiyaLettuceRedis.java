package com.miya.annotation;

import com.miya.configure.MiyaLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Caixiaowei
 * @ClassName EnableMiyaLettuceRedis.java
 * @Description 驱动lettuce redis 配置
 * @createTime 2020年05月13日 15:51:00
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MiyaLettuceRedisConfigure.class)
public @interface EnableMiyaLettuceRedis {
}
