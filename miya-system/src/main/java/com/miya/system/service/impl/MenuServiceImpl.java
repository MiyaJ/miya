package com.miya.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.miya.entity.router.RouterMeta;
import com.miya.entity.router.VueRouter;
import com.miya.entity.system.Menu;
import com.miya.system.mapper.MenuMapper;
import com.miya.system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-14
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * @param username
     * @title 根据用户名查找路由
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/4 16:36
     */
    @Override
    public List<VueRouter<Menu>> getUserRouters(String username) {
        List<VueRouter<Menu>> routes = Lists.newArrayList();
        List<Menu> menus = findUserMenus(username);
        menus.forEach(menu -> {
            VueRouter<Menu> router = new VueRouter<>();
            router.setId(menu.getId().toString());
            router.setParentId(menu.getParentId().toString());
            router.setIcon(menu.getIcon());
            router.setPath(menu.getPath());
            router.setComponent(menu.getComponent());
            router.setName(menu.getMenuName());
            router.setMeta(new RouterMeta(true, null));
            routes.add(router);
        });

        return routes;
    }

    @Override
    public List<Menu> findUserMenus(String username) {
        return menuMapper.findUserMenus(username);
    }

    /**
     * @param username
     * @title 根据用户名查找权限
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/4 16:35
     */
    @Override
    public List<Menu> findUserPermissions(String username) {
        return menuMapper.findUserPermissions(username);
    }
}
