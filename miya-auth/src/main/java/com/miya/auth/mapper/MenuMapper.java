package com.miya.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miya.entity.system.Menu;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-13
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 通过用户名查找用户权限集合
     *
     * @param username 用户名
     * @return List<Menu> 用户权限集合
     * @author Caixiaowei
     * @updateTime 2021/1/7 14:19
     */
    List<Menu> findUserPermissions(String username);
}
