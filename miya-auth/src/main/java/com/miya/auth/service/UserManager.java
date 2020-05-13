package com.miya.auth.service;

import com.miya.auth.mapper.MenuMapper;
import com.miya.auth.mapper.UserMapper;
import com.miya.entity.system.Menu;
import com.miya.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Caixiaowei
 * @ClassName UserManager.java
 * @Description 系统用户管理业务层
 * @createTime 2020年05月13日 13:43:00
 */
@Service
public class UserManager {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    /**
     * @title 根据用户名查找用户
     * @description
     * @author Caixiaowei
     * @updateTime 2020/5/13 13:44
     * @throws
     */
    public SystemUser findByName(String username) {
        return userMapper.findByName(username);
    }

    /**
     * @title 根据用户名查找用户权限
     * @description
     * @author Caixiaowei
     * @updateTime 2020/5/13 13:45
     * @throws
     */
    public String findUserPermissions(String username) {
        List<Menu> userPermissions = menuMapper.findUserPermissions(username);
        return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(","));
    }
}
