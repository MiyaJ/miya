package com.miya.auth.controller;

import com.miya.entity.MiyaResponse;
import com.miya.exception.MiyaAuthException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author Caixiaowei
 * @ClassName SecurityController.java
 * @Description TODO
 * @createTime 2020年05月12日 13:23:00
 */
@RestController
public class SecurityController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signout")
    public MiyaResponse signout(HttpServletRequest request) throws MiyaAuthException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "Bearer ", "");
        MiyaResponse miyaResponse = new MiyaResponse();
        if (!consumerTokenServices.revokeToken(token)) {
            throw new MiyaAuthException("退出登录失败");
        }
        return miyaResponse.message("退出登录成功");
    }
}
