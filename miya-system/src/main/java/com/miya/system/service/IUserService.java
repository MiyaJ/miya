package com.miya.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.miya.entity.QueryRequest;
import com.miya.entity.system.SystemUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-14
 */
public interface IUserService extends IService<SystemUser> {

    /**
     * @title 分页查找用户信息
     * @description
     * @author Caixiaowei
     * @updateTime 2020/5/14 10:44
     * @throws
     */
    IPage<SystemUser> findUserDetailPage(SystemUser user, QueryRequest request);

    SystemUser findByName(String username);
}
