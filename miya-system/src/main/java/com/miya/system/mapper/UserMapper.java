package com.miya.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miya.entity.system.SystemUser;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-14
 */
public interface UserMapper extends BaseMapper<SystemUser> {

    IPage<SystemUser> findUserDetailPage(Page page, @Param("user") SystemUser user);
}
