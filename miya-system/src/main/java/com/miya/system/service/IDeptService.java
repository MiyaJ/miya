package com.miya.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.miya.entity.QueryRequest;
import com.miya.entity.system.Dept;

import java.util.Map;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-14
 */
public interface IDeptService extends IService<Dept> {

    /**
     * @title 部门列表
     * @description
     * @author Caixiaowei
     * @param dept: 部门
     * @param request: 分页排序条件
     * @updateTime 2020/6/5 22:56
     * @return: Map<String, Object>
     */
    Map<String, Object> deptList(Dept dept, QueryRequest request);

    /**
     * @title 新增部门
     * @description
     * @author Caixiaowei
     * @param dept: 部门信息
     * @updateTime 2020/6/5 22:57
     * @return: void
     */
    void add(Dept dept);

    /**
     * @title 修改部门
     * @description
     * @author Caixiaowei
     * @param dept: 部门信息
     * @updateTime 2020/6/5 22:56
     * @return: void
     */
    void update(Dept dept);

    /**
     * @title
     * @description
     * @author Caixiaowei
     * @param deptIds: 部门ids
     * @updateTime 2020/6/5 22:58
     * @return: void
     */
    void deleteDepts(String[] deptIds);
}
