package com.miya.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miya.entity.system.UserRole;
import com.miya.system.mapper.UserRoleMapper;
import com.miya.system.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-14
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
