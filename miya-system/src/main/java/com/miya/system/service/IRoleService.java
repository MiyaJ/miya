package com.miya.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.miya.entity.system.Role;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-14
 */
public interface IRoleService extends IService<Role> {

    List<Role> findUserRole(String username);
}
