package com.miya.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.miya.constant.PageConstant;
import com.miya.entity.router.RouterMeta;
import com.miya.entity.router.VueRouter;
import com.miya.entity.system.Menu;
import com.miya.entity.tree.MenuTree;
import com.miya.entity.tree.Tree;
import com.miya.system.mapper.MenuMapper;
import com.miya.system.service.IMenuService;
import com.miya.utils.TreeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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

    /**
     * @param menu : 菜单实体
     * @return List<Menu>: 菜单集合
     * @title
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/4 22:41
     */
    @Override
    public Map<String, Object> findMenus(Menu menu) {
        Map<String, Object> result = Maps.newHashMap();
        try {
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.orderByAsc(Menu::getOrderNum);
            List<Menu> menus = menuMapper.selectList(queryWrapper);
            List<MenuTree> trees = Lists.newArrayList();
            buildTrees(trees, menus);
            if (StringUtils.equals(menu.getType(), Menu.TYPE_BUTTON)) {
                result.put(PageConstant.ROWS, trees);
            } else {
                List<? extends Tree<?>> menuTree = TreeUtil.build(trees);
                result.put(PageConstant.ROWS, menuTree);
            }
            result.put(PageConstant.TOTAL, menus.size());
        } catch (Exception e) {
            result.put(PageConstant.ROWS, null);
            result.put(PageConstant.TOTAL, 0);
        }
        return result;
    }

    /**
     * @param menu 菜单/按钮 实体
     * @title 新增菜单/按钮
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/5 10:15
     */
    @Override
    public void add(Menu menu) {
        menu.setCreateTime(LocalDateTime.now());
        setMenu(menu);
        menuMapper.insert(menu);
    }

    /**
     * @param menu 菜单/按钮 实体
     * @title 修改菜单/按钮
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/5 10:15
     */
    @Override
    public void update(Menu menu) {
        menu.setUpdateTime(LocalDateTime.now());
        menuMapper.updateById(menu);
    }

    /**
     * @param menuIds 菜单/按钮ids
     * @title 新增菜单/按钮
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/5 10:15
     */
    @Override
    public void delete(String[] menuIds) {
        // 删除并递归删除子菜单/按钮
        this.delete(Arrays.asList(menuIds));

    }

    /******************************************** 私有方法 ******************************************************/
    private void buildTrees(List<MenuTree> trees, List<Menu> menus) {
        menus.forEach(menu -> {
            MenuTree tree = new MenuTree();
            tree.setId(menu.getId().toString());
            tree.setParentId(menu.getParentId().toString());
            tree.setLabel(menu.getMenuName());
            tree.setComponent(menu.getComponent());
            tree.setIcon(menu.getIcon());
            tree.setOrderNum(menu.getOrderNum());
            tree.setPath(menu.getPath());
            tree.setType(menu.getType());
            tree.setPerms(menu.getPerms());
            trees.add(tree);
        });
    }

    /**
     * @title 设置菜单/按钮
     * @description
     * @author Caixiaowei
     * @param
     * @updateTime 2020/6/5 10:28
     */
    private void setMenu(Menu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(Menu.TOP_MENU_ID);
        }
        if (Menu.TYPE_BUTTON.equals(menu.getType())) {
            menu.setPath(null);
            menu.setIcon(null);
            menu.setComponent(null);
            menu.setOrderNum(null);
        }
    }

    /**
     * @title 根据id 删除菜单/按钮
     * @description
     * @author Caixiaowei
     * @param menuIds
     * @updateTime 2020/6/5 10:28
     */
    private void delete(List<String> menuIds) {
        removeByIds(menuIds);

        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Menu::getParentId, menuIds);
        List<Menu> menus = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(menus)) {
            List<String> menuIdList = new ArrayList<>();
            menus.forEach(m -> menuIdList.add(String.valueOf(m.getId())));
            this.delete(menuIdList);
        }
    }
}
