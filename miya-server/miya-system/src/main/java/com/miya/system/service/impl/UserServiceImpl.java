package com.miya.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miya.entity.QueryRequest;
import com.miya.entity.system.SystemUser;
import com.miya.system.mapper.UserMapper;
import com.miya.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SystemUser> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @param request
     * @param user
     * @throws
     * @title 分页查找用户信息
     * @description
     * @author Caixiaowei
     * @updateTime 2020/5/14 10:44
     */
    @Override
    public IPage<SystemUser> findUserDetailPage(SystemUser user, QueryRequest request) {
        Page<SystemUser> page = new Page<>(request.getPageNum(), request.getPageSize());
        return userMapper.findUserDetailPage(page, user);
    }

    @Override
    public SystemUser findByName(String username) {
        return null;
    }
}
