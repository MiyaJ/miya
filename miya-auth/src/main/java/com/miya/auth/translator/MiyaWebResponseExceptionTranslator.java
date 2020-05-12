package com.miya.auth.translator;

import com.miya.entity.MiyaResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * @author Caixiaowei
 * @ClassName MiyaWebResponseExceptionTranslator.java
 * @Description 认证异常信息翻译类
 * @createTime 2020年05月12日 17:46:00
 */
@Slf4j
@Component
public class MiyaWebResponseExceptionTranslator implements WebResponseExceptionTranslator {

    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        ResponseEntity.BodyBuilder status = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        MiyaResponse response = new MiyaResponse();
        String message = "认证失败";
        log.error(message, e);
        if (e instanceof UnsupportedGrantTypeException) {
            message = "不支持该认证类型";
            return status.body(response.message(message));
        }
        if (e instanceof InvalidGrantException) {
            if (StringUtils.containsIgnoreCase(e.getMessage(), "Invalid refresh token")) {
                message = "refresh token无效";
                return status.body(response.message(message));
            }
            if (StringUtils.containsIgnoreCase(e.getMessage(), "locked")) {
                message = "用户已被锁定，请联系管理员";
                return status.body(response.message(message));
            }
            message = "用户名或密码错误";
            return status.body(response.message(message));
        }
        return status.body(response.message(message));
    }
}
