package com.miya.handler;

import com.miya.entity.MiyaResponse;
import com.miya.exception.MiyaAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Caixiaowei
 * @ClassName BaseExceptionHandler.java
 * @Description 基础异常类
 * @createTime 2020年05月12日 18:16:00
 */
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MiyaResponse handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return MiyaResponse.error("系统内部异常");
    }

    @ExceptionHandler(value = MiyaAuthException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public MiyaResponse handleMiyaAuthException(MiyaAuthException e) {
        log.error("系统错误", e);
        return MiyaResponse.error(e.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public MiyaResponse handleAccessDeniedException(){
        return MiyaResponse.error("没有权限访问该资源");
    }
}
