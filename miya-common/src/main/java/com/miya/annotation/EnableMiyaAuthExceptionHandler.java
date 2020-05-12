package com.miya.annotation;

import com.miya.configure.MiyaAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Caixiaowei
 * @ClassName EnableMiyaAuthExceptionHandler.java
 * @Description 认证类型异常翻译
 * @createTime 2020年05月12日 18:06:00
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MiyaAuthExceptionConfigure.class)
public @interface EnableMiyaAuthExceptionHandler {
}
