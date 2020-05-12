package com.miya.auth.handler;

import com.miya.handler.BaseExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Caixiaowei
 * @ClassName GlobalExceptionHandler.java
 * @Description 全局异常
 * @createTime 2020年05月12日 18:18:00
 */
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends BaseExceptionHandler {
}
