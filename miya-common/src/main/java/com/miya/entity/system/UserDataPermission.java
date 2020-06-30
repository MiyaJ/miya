package com.miya.entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>
 * 用户部门关系
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-14
 */
@Data
@TableName("t_user_data_permission")
public class UserDataPermission {

    @TableId("user_id")
    private Long userId;
    @TableId("dept_id")
    private Long deptId;

}