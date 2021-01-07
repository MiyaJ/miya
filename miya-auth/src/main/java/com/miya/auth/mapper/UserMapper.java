package com.miya.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miya.entity.system.SystemUser;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-13
 */
public interface UserMapper extends BaseMapper<SystemUser> {

    /**
     * 通过用户名查找用户信息
     *
     * @param username 用户名
     * @return SystemUser 用户信息
     * @author Caixiaowei
     * @updateTime 2021/1/7 14:19
     */
    SystemUser findByName(String username);
}
