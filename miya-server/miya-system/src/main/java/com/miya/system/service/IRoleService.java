package com.miya.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.miya.entity.QueryRequest;
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

    /**
     * @title 分页查找角色
     * @description
     * @author Caixiaowei
     * @param role: 角色条件
     * @param queryRequest 分页条件
     * @updateTime 2020/6/5 15:22
     */
    IPage<Role> findRoles(Role role, QueryRequest queryRequest);

    /**
     * @title 查找所有角色
     * @description
     * @author Caixiaowei
     * @param
     * @updateTime 2020/6/5 15:46
     */
    List<Role> allRoles();

    /**
     * @title 角色名找角色
     * @description
     * @author Caixiaowei
     * @param roleName: 角色名
     * @updateTime 2020/6/5 15:50
     */
    Role findByName(String roleName);

    /**
     * @title 新增角色
     * @description
     * @author Caixiaowei
     * @param role
     * @updateTime 2020/6/5 16:17
     */
    void add(Role role);

    /**
     * @title 修改角色
     * @description
     * @author Caixiaowei
     * @param role
     * @updateTime 2020/6/5 16:17
     */
    void updateRole(Role role);

    /**
     * @title 删除角色
     * @description
     * @author Caixiaowei
     * @param roleIds
     * @updateTime 2020/6/5 16:17
     */
    void deleteRoles(String[] roleIds);
}
