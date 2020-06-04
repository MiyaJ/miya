package com.miya.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miya.entity.system.Role;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-14
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * @title 根据用户名查找用户角色
     * @description
     * @author Caixiaowei
     * @param username 用户名
     * @updateTime 2020/6/4 17:34
     */
    List<Role> findUserRole(String username);
}
