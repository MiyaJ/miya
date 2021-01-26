package com.miya.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.miya.constant.StringConstant;
import com.miya.entity.MiyaResponse;
import com.miya.entity.QueryRequest;
import com.miya.entity.system.Role;
import com.miya.system.manager.UserManager;
import com.miya.system.service.IRoleService;
import com.miya.utils.MiyaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Caixiaowei
 * @ClassName RoleController.java
 * @Description 角色管理
 * @createTime 2020年06月05日 14:38:00
 */
@Api(value = "角色管理", tags = "角色管理")
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "角色列表")
    @GetMapping("/findRoles")
    public MiyaResponse findRoles(QueryRequest queryRequest, Role role) {
        IPage<Role> page = roleService.findRoles(role, queryRequest);
        Map<String, Object> dataTable = MiyaUtil.getDataTable(page);
        return MiyaResponse.success(dataTable);
    }

    @ApiOperation(value = "查询所有角色")
    @GetMapping("/allRoles")
    public MiyaResponse allRoles() {
        List<Role> roles = roleService.allRoles();
        return MiyaResponse.success(roles);
    }

    @ApiOperation(value = "检查角色名")
    @GetMapping("/checkRoleName")
    public MiyaResponse checkRoleName(String roleName) {
        Role role = roleService.findByName(roleName);
        return MiyaResponse.success(role == null);
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("/add")
    public MiyaResponse add(@RequestBody Role Role) {
        roleService.add(Role);
        return MiyaResponse.success();
    }

    @ApiOperation(value = "修改角色")
    @PostMapping("/update")
    public MiyaResponse update(@RequestBody Role Role) {
        roleService.updateRole(Role);
        return MiyaResponse.success();
    }

    @ApiOperation(value = "删除角色")
    @GetMapping("/delete")
    public MiyaResponse delete(String roleIds) {
        String[] ids = roleIds.split(StringConstant.COMMA);
        roleService.deleteRoles(ids);
        return MiyaResponse.success();
    }

}
