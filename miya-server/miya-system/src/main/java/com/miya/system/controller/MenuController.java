package com.miya.system.controller;

import com.google.common.collect.Maps;
import com.miya.constant.StringConstant;
import com.miya.entity.MiyaResponse;
import com.miya.entity.router.VueRouter;
import com.miya.entity.system.Menu;
import com.miya.system.manager.UserManager;
import com.miya.system.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@Api(value = "菜单/权限管理", tags = "菜单/权限管理")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "获取用户路由", response = MiyaResponse.class)
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "path")
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
    @ApiOperation(value = "菜单列表")
    @GetMapping
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
    @ApiOperation(value = "用户权限集", response = Menu.class)
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query")
    @GetMapping("/permissions")
    public MiyaResponse findUserPermissions(String username) {
        List<Menu> menus = menuService.findUserPermissions(username);
        String permissions = menus.stream().map(Menu::getPerms).collect(Collectors.joining(","));
        return MiyaResponse.success(permissions);
    }

    /**
     * @title 新增菜单/按钮
     * @description
     * @author Caixiaowei
     * @param menu
     * @updateTime 2020/6/5 9:52
     */
    @ApiOperation(value = "新增菜单/按钮")
    @PostMapping("/add")
    public MiyaResponse add(@RequestBody Menu menu) {
        menuService.add(menu);
        return MiyaResponse.success();
    }

    /**
     * @title 修改菜单/按钮
     * @description
     * @author Caixiaowei
     * @param menu
     * @updateTime 2020/6/5 9:53
     */
    @ApiOperation(value = "修改菜单/按钮")
    @PostMapping("/update")
    public MiyaResponse update(@RequestBody Menu menu) {
        menuService.update(menu);
        return MiyaResponse.success();
    }

    /**
     * @title 删除菜单/按钮
     * @description
     * @author Caixiaowei
     * @param menuIds 菜单/按钮 ids
     * @updateTime 2020/6/5 9:53
     */
    @ApiOperation(value = "删除菜单/按钮")
    @ApiImplicitParam(name = "menuIds", value = "菜单ids", required = true, dataType = "String", paramType = "query")
    @DeleteMapping("/delete")
    public MiyaResponse delete(String menuIds) {
        String[] ids = menuIds.split(StringConstant.COMMA);
        menuService.delete(ids);
        return MiyaResponse.success();
    }
}
