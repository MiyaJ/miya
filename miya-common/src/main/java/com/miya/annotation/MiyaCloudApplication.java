package com.miya.annotation;

import com.miya.selector.MiyaCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Caixiaowei
 * @ClassName MiyaCloudApplication.java
 * @Description TODO
 * @createTime 2020年05月13日 15:19:00
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MiyaCloudApplicationSelector.class)
public @interface MiyaCloudApplication {
}
