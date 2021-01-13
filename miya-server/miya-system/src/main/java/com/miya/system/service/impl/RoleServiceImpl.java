package com.miya.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.miya.constant.MiyaConstant;
import com.miya.constant.StringConstant;
import com.miya.entity.QueryRequest;
import com.miya.entity.system.Role;
import com.miya.entity.system.RoleMenu;
import com.miya.system.mapper.RoleMapper;
import com.miya.system.service.IRoleMenuService;
import com.miya.system.service.IRoleService;
import com.miya.system.service.IUserRoleService;
import com.miya.utils.SortUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-14
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public List<Role> findUserRole(String username) {
        return null;
    }

    /**
     * @param role         : 角色条件
     * @param queryRequest : 分页条件
     * @title 分页查找角色
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/5 15:22
     */
    @Override
    public IPage<Role> findRoles(Role role, QueryRequest queryRequest) {
        Page<Role> page = new Page<>(queryRequest.getPageNum(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "createTime", MiyaConstant.ORDER_DESC, false);
        return roleMapper.findRolePage(page, role);
    }

    /**
     * @title 查找所有角色
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/5 15:46
     */
    @Override
    public List<Role> allRoles() {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.orderByAsc(Role::getId);
        return roleMapper.selectList(queryWrapper);
    }

    /**
     * @param roleName : 角色名
     * @title 角色名找角色
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/5 15:50
     */
    @Override
    public Role findByName(String roleName) {
        return roleMapper.selectOne(new LambdaQueryWrapper<Role>().eq(Role::getRoleName, roleName));
    }

    /**
     * @param role
     * @title 新增角色
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/5 16:17
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Role role) {
        role.setCreateTime(LocalDateTime.now());
        this.save(role);

        // 维护角色菜单关系
        if (StringUtils.isNotBlank(role.getMenuIds())) {
            String[] menuIds = StringUtils.splitByWholeSeparatorPreserveAllTokens(role.getMenuIds(), StringConstant.COMMA);
            setRoleMenus(role, menuIds);
        }
    }

    /**
     * @param role
     * @title 修改角色
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/5 16:17
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(Role role) {
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.updateById(role);

        // 维护角色权限关系
        roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, role.getId()));
        if (StringUtils.isNotBlank(role.getMenuIds())) {
            String[] menuIds = StringUtils.splitByWholeSeparatorPreserveAllTokens(role.getMenuIds(), StringConstant.COMMA);
            setRoleMenus(role, menuIds);
        }

    }

    /**
     * @param roleIds
     * @title 删除角色
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/5 16:17
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoles(String[] roleIds) {
        List<String> list = Arrays.asList(roleIds);
        roleMapper.deleteBatchIds(list);

        // 维护角色菜单关系, 用户角色关系
        this.roleMenuService.deleteRoleMenusByRoleId(roleIds);
        this.userRoleService.deleteUserRolesByRoleId(roleIds);

    }

    /****************************************** 私有方法 ******************************************/

    /**
     * @title 保存角色菜单关系
     * @description
     * @author Caixiaowei
     * @param role 角色对象
     * @param menuIds 菜单ids
     * @updateTime 2020/6/5 16:23
     */
    private void setRoleMenus(Role role, String[] menuIds) {
        List<RoleMenu> roleMenus = Lists.newArrayList();
        Arrays.stream(menuIds).forEach(menuId -> {
            RoleMenu roleMenu = new RoleMenu();
            if (StringUtils.isNotBlank(menuId)) {
                roleMenu.setMenuId(Long.valueOf(menuId));
            }
            roleMenu.setRoleId(role.getId());
            roleMenus.add(roleMenu);
        });
        roleMenuService.saveBatch(roleMenus);
    }
}
