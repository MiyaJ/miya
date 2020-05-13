package com.miya.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_role_menu")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ROLE_ID")
    private Long roleId;

    @TableField("MENU_ID")
    private Long menuId;


}
