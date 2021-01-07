package com.miya.interceptor;

import com.miya.constant.MiyaConstant;
import com.miya.entity.MiyaResponse;
import com.miya.utils.MiyaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Caixiaowei
 * @ClassName MiyaServerProtectInterceptor.java
 * @Description 服务保护拦截器,验证请求是否是从网关过来的
 * @createTime 2020年05月12日 19:02:00
 */
@Slf4j
public class MiyaServerProtectInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取 gateway Token
        String token = request.getHeader(MiyaConstant.GATEWAY_TOKEN_HEADER);
        String gatewayToken = new String(Base64Utils.encode(MiyaConstant.GATEWAY_TOKEN_VALUE.getBytes()));
        // 校验 gateway Token的正确性
        if (StringUtils.equals(gatewayToken, token)) {
            return true;
        } else {
            MiyaUtil.makeResponse(response, MediaType.APPLICATION_JSON_VALUE, HttpServletResponse.SC_FORBIDDEN,
                    MiyaResponse.error("请通过网关获取资源"));
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
