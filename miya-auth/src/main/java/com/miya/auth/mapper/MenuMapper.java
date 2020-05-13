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

    List<Menu> findUserPermissions(String username);
}
