package com.miya.handler;

import com.miya.entity.MiyaResponse;
import com.miya.utils.MiyaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Caixiaowei
 * @ClassName MiyaAuthExceptionEntryPoint.java
 * @Description 401 异常翻译
 * @createTime 2020年05月12日 17:49:00
 */
@Slf4j
public class MiyaAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        MiyaUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_VALUE,
                HttpServletResponse.SC_UNAUTHORIZED, MiyaResponse.error("token无效"));
    }
}
