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

    SystemUser findByName(String username);
}
