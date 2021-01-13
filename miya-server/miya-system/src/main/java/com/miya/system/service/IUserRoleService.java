package com.miya.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.miya.entity.system.UserRole;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-14
 */
public interface IUserRoleService extends IService<UserRole> {

    void deleteUserRolesByRoleId(String[] roleIds);
}
