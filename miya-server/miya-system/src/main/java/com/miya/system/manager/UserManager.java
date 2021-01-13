package com.miya.system.manager;

import com.miya.entity.system.Menu;
import com.miya.entity.system.Role;
import com.miya.entity.system.SystemUser;
import com.miya.system.service.ICacheService;
import com.miya.system.service.IMenuService;
import com.miya.system.service.IRoleService;
import com.miya.system.service.IUserService;
import com.miya.system.utils.CashUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Caixiaowei
 * @ClassName UserManager.java
 * @Description 系统用户管理
 * @createTime 2020年06月04日 16:58:00
 */
@Service
@Slf4j
public class UserManager {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICacheService cacheService;

    public Set<String> getUserRoles(String username) {
        List<Role> roleList = CashUtil.selectCacheByTemplate(
                () -> this.cacheService.getRoles(username),
                () -> this.roleService.findUserRole(username));
        return roleList.stream().map(Role::getRoleName).collect(Collectors.toSet());
    }

    public Set<String> getUserPermissions(String username) {
        List<Menu> permissionList = CashUtil.selectCacheByTemplate(
                () -> this.cacheService.getPermissions(username),
                () -> this.menuService.findUserPermissions(username));
        return permissionList.stream().map(Menu::getPerms).collect(Collectors.toSet());
    }

    public SystemUser getUser(String username) {
        return CashUtil.selectCacheByTemplate(() -> this.cacheService.getUser(username),
                () -> this.userService.findByName(username));
    }
}
