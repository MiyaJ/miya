package com.miya.handler;

import com.miya.entity.MiyaResponse;
import com.miya.utils.MiyaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Caixiaowei
 * @ClassName MiyaAccessDeniedHandler.java
 * @Description 403 异常处理
 * @createTime 2020年05月12日 17:55:00
 */
@Slf4j
public class MiyaAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        MiyaResponse miyaResponse = new MiyaResponse();
        MiyaUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_FORBIDDEN, miyaResponse.message("没有权限访问该资源"));
    }
}
