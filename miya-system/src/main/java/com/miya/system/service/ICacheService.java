package com.miya.system.service;

import com.miya.entity.system.Menu;
import com.miya.entity.system.Role;
import com.miya.entity.system.SystemUser;

import java.util.List;

/**
 * @author Caixiaowei
 * @ClassName ICashService.java
 * @Description TODO
 * @createTime 2020年06月04日 17:08:00
 */
public interface ICacheService {

    /**
     * 从缓存中获取用户
     * @param username 用户名
     * @return
     * @throws Exception
     */
    SystemUser getUser(String username) throws Exception;

    /**
     * 从缓存中获取用户角色
     * @param username 用户名
     * @return
     * @throws Exception
     */
    List<Role> getRoles(String username) throws Exception;

    /**
     * 从缓存中获取用户权限
     * @param username 用户名
     * @return 权限集
     */
    List<Menu> getPermissions(String username) throws Exception;

    void saveUser(String username) throws Exception;

    void saveRoles(String username) throws Exception;

    void savePermissions(String username) throws Exception;

    /**
     * 删除用户信息
     *
     * @param username 用户名
     */
    void deleteUser(String username) throws Exception;

    /**
     * 删除用户角色信息
     *
     * @param username 用户名
     */
    void deleteRoles(String username) throws Exception;

    /**
     * 删除用户权限信息
     *
     * @param username 用户名
     */
    void deletePermissions(String username) throws Exception;

    /**
     * 删除用户个性化配置
     *
     * @param userId 用户 ID
     */
    void deleteUserConfigs(String userId) throws Exception;
}
