package com.miya.system.controller;

import com.google.common.collect.Maps;
import com.miya.entity.MiyaResponse;
import com.miya.entity.router.VueRouter;
import com.miya.entity.system.Menu;
import com.miya.system.manager.UserManager;
import com.miya.system.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author Caixiaowei
 * @ClassName MenuController.java
 * @Description 菜单资源
 * @createTime 2020年06月04日 16:28:00
 */
@RestController
@RequestMapping("/menu")
@Slf4j
@Validated
public class MenuController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private IMenuService menuService;

    @GetMapping("/{username}")
    public MiyaResponse getUserRouter(@NotBlank(message = "{required") @PathVariable String username) {
        Map<String, Object> result = Maps.newHashMap();
        List<VueRouter<Menu>> userRouters = this.menuService.getUserRouters(username);
//        String userPermissions = this.menuService.findUserPermissions(username);


        return MiyaResponse.success();
    }
}
