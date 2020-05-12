package com.miya.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Caixiaowei
 * @ClassName TestController.java
 * @Description 测试restful
 * @createTime 2020年05月12日 16:44:00
 */
@RestController
public class TestController {

    @GetMapping("info")
    public String test(){
        return "miya-system";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }
}
