package com.miya.system.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miya.constant.AdminConstant;
import com.miya.entity.system.Menu;
import com.miya.entity.system.Role;
import com.miya.entity.system.SystemUser;
import com.miya.service.RedisService;
import com.miya.system.mapper.MenuMapper;
import com.miya.system.mapper.RoleMapper;
import com.miya.system.mapper.UserMapper;
import com.miya.system.service.ICacheService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Caixiaowei
 * @ClassName CashServiceImpl.java
 * @Description TODO
 * @createTime 2020年06月04日 17:09:00
 */
@Service
@Slf4j
public class CacheServiceImpl implements ICacheService {

    private Long effectiveTime = 0L;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleService;
    @Autowired
    private MenuMapper menuService;
    @Autowired
    private RedisService redisService;

    /**
     * 从缓存中获取用户信息
     * @param username 用户名
     * @return
     * @throws Exception
     */
    @Override
    public SystemUser getUser(String username) throws Exception {
        String userString = redisService.get(AdminConstant.USER_CACHE_PREFIX + username).toString();
        if (StringUtils.isBlank(userString)) {
            //            throw new Exception();
            return null;
        } else {
            return this.mapper.readValue(userString, SystemUser.class);
        }
    }

    /**
     * 从缓存中获取用户角色信息
     * @param username 用户名
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> getRoles(String username) throws Exception {
        String roleListString = redisService.get(AdminConstant.USER_ROLE_CACHE_PREFIX + username).toString();
        if (StringUtils.isBlank(roleListString)) {
//            throw new Exception();
            return null;
        } else {
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, Role.class);
            return this.mapper.readValue(roleListString, type);
        }
    }

    /**
     * 从缓存中获取用户权限
     * @param username 用户名
     * @return
     * @throws Exception
     */
    @Override
    public List<Menu> getPermissions(String username) throws Exception {
        String permissionListString = redisService.get(AdminConstant.USER_PERMISSION_CACHE_PREFIX + username).toString();
        if (StringUtils.isBlank(permissionListString)) {
//            throw new Exception();
            return null;
        } else {
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, SystemUser.class);
            return this.mapper.readValue(permissionListString, type);
        }
    }

    /**
     * 缓存用户信息
     * @param username
     */
    @Override
    public void saveUser(String username) throws Exception {
        SystemUser user = userMapper.findDetail(username);
        this.deleteUser(username);
        redisService.set(AdminConstant.USER_CACHE_PREFIX + username, mapper.writeValueAsString(user), effectiveTime);
    }

    /**
     * 缓存用户角色信息
     * @param username
     * @throws Exception
     */
    @Override
    public void saveRoles(String username) throws Exception {
        List<Role> roleList = this.roleService.findUserRole(username);
        if (!roleList.isEmpty()) {
            this.deleteRoles(username);
            redisService.set(AdminConstant.USER_ROLE_CACHE_PREFIX + username, mapper.writeValueAsString(roleList), effectiveTime);
        }
    }

    /**
     * 缓存用户权限信息
     * @param username
     * @throws Exception
     */
    @Override
    public void savePermissions(String username) throws Exception {
        List<Menu> permissionList = this.menuService.findUserPermissions(username);
        if (!permissionList.isEmpty()) {
            this.deletePermissions(username);
            redisService.set(AdminConstant.USER_PERMISSION_CACHE_PREFIX + username,
                    mapper.writeValueAsString(permissionList), effectiveTime);
        }
    }

    /**
     * 删除用户缓存
     * @param username 用户名
     */
    @Override
    public void deleteUser(String username) {
        username = username.toLowerCase();
        redisService.delete(AdminConstant.USER_CACHE_PREFIX + username);
    }

    /**
     * 删除用户角色信息
     *
     * @param username 用户名
     */
    @Override
    public void deleteRoles(String username) throws Exception {
        username = username.toLowerCase();
        redisService.delete(AdminConstant.USER_ROLE_CACHE_PREFIX + username);
    }

    /**
     * 删除用户权限信息
     *
     * @param username 用户名
     */
    @Override
    public void deletePermissions(String username) throws Exception {
        username = username.toLowerCase();
        redisService.delete(AdminConstant.USER_PERMISSION_CACHE_PREFIX + username);
    }

    /**
     * 删除用户个性化配置
     *
     * @param userId 用户 ID
     */
    @Override
    public void deleteUserConfigs(String userId) throws Exception {
        redisService.delete(AdminConstant.USER_CONFIG_CACHE_PREFIX + userId);
    }
}
