package com.miya.system.controller;

import com.google.common.collect.Maps;
import com.miya.entity.MiyaResponse;
import com.miya.entity.router.VueRouter;
import com.miya.entity.system.Menu;
import com.miya.system.manager.UserManager;
import com.miya.system.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<Menu> menus = this.menuService.findUserPermissions(username);
        String userPermissions = menus.stream().map(Menu::getPerms).collect(Collectors.joining(","));
        String[] permissionArray = new String[0];
        if (StringUtils.isNoneBlank(userPermissions)) {
            permissionArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(userPermissions, ",");
        }
        result.put("routes", userRouters);
        result.put("permissions", permissionArray);
        return MiyaResponse.success(result);
    }

    /**
     * @title 菜单列表
     * @description 根据条件查询menu 集合
     * @author Caixiaowei
     * @param menu: 菜单
     * @updateTime 2020/6/4 22:34
     * @return:
     */
    public MiyaResponse menuList(Menu menu) {
        Map<String, Object> menus = menuService.findMenus(menu);
        return MiyaResponse.success(menus);
    }

    /**
     * @title 用户权限集
     * @description
     * @author Caixiaowei
     * @param username: 用户名
     * @updateTime 2020/6/4 23:41
     * @return:
     */
    @GetMapping("/permissions")
    public MiyaResponse findUserPermissions(String username) {
        List<Menu> menus = menuService.findUserPermissions(username);
        String permissions = menus.stream().map(Menu::getPerms).collect(Collectors.joining(","));
        return MiyaResponse.success(permissions);
    }
}
