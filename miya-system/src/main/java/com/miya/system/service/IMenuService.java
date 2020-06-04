package com.miya.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.miya.entity.router.VueRouter;
import com.miya.entity.system.Menu;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-14
 */
public interface IMenuService extends IService<Menu> {

    /**
     * @title 根据用户名查找菜单资源
     * @description
     * @author Caixiaowei
     * @param username 用户名
     * @updateTime 2020/6/4 16:49
     * @return List<Menu>
     */
    List<Menu> findUserMenus(String username);

    /**
     * @title 根据用户名查找路由
     * @description
     * @author Caixiaowei
     * @param username 用户名
     * @updateTime 2020/6/4 16:36
     */
    List<VueRouter<Menu>> getUserRouters(String username);

    /**
     * @title 根据用户名查找权限
     * @description
     * @author Caixiaowei
     * @param username 用户名
     * @updateTime 2020/6/4 16:35
     */
    List<Menu> findUserPermissions(String username);

    /**
     * @title
     * @description
     * @author Caixiaowei
     * @param menu: 菜单实体
     * @updateTime 2020/6/4 22:41
     * @return: List<Menu> 菜单集合
     */
    Map<String, Object> findMenus(Menu menu);
}
