package com.miya.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.miya.constant.MiyaConstant;
import com.miya.constant.PageConstant;
import com.miya.entity.QueryRequest;
import com.miya.entity.system.Dept;
import com.miya.entity.tree.DeptTree;
import com.miya.entity.tree.Tree;
import com.miya.system.mapper.DeptMapper;
import com.miya.system.service.IDeptService;
import com.miya.system.service.IUserDataPermissionService;
import com.miya.utils.SortUtil;
import com.miya.utils.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-14
 */
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private IUserDataPermissionService userDataPermissionService;

    /**
     * @param dept    : 部门
     * @param request : 分页排序条件
     * @title 部门列表
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/5 22:56
     * @return: Map<String, Object>
     */
    @Override
    public Map<String, Object> deptList(Dept dept, QueryRequest request) {
        Map<String, Object> result = Maps.newHashMap();
        try {
            List<Dept> depts = findDepts(dept, request);
            List<DeptTree> trees = Lists.newArrayList();
            buildTrees(trees, depts);
            List<? extends Tree<?>> deptTree = TreeUtil.build(trees);

            result.put(PageConstant.ROWS, deptTree);
            result.put(PageConstant.TOTAL, depts.size());
        } catch (Exception e) {
            log.error("获取部门列表失败", e);
            result.put(PageConstant.ROWS, null);
            result.put(PageConstant.TOTAL, 0);
        }
        return result;

    }


    /**
     * @param dept : 部门信息
     * @title 新增部门
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/5 22:57
     * @return: void
     */
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        if (dept.getParentId() == null) {
            dept.setParentId(Dept.TOP_DEPT_ID);
        }
        this.save(dept);
    }

    /**
     * @param dept : 部门信息
     * @title 修改部门
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/5 22:56
     * @return: void
     */
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        if (dept.getParentId() == null) {
            dept.setParentId(Dept.TOP_DEPT_ID);
        }
        baseMapper.updateById(dept);
    }

    /**
     * @param deptIds : 部门ids
     * @title
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/5 22:58
     * @return: void
     */
    @Override
    public void deleteDepts(String[] deptIds) {
        this.delete(Arrays.asList(deptIds));
    }

    /************************************* 私有方法 *********************************************/

    private List<Dept> findDepts(Dept dept, QueryRequest request) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(dept.getDeptName())) {
            queryWrapper.lambda().like(Dept::getDeptName, dept.getDeptName());
        }
        if (StringUtils.isNotBlank(dept.getCreateTimeFrom()) && StringUtils.isNotBlank(dept.getCreateTimeTo())) {
            queryWrapper.lambda()
                    .ge(Dept::getCreateTime, dept.getCreateTimeFrom())
                    .le(Dept::getCreateTime, dept.getCreateTimeTo());
        }
        SortUtil.handleWrapperSort(request, queryWrapper, "orderNum", MiyaConstant.ORDER_ASC, true);
        return baseMapper.selectList(queryWrapper);

    }

    private void buildTrees(List<DeptTree> trees, List<Dept> depts) {
        depts.forEach(dept -> {
            DeptTree tree = new DeptTree();
            tree.setId(dept.getId().toString());
            tree.setParentId(dept.getParentId().toString());
            tree.setLabel(dept.getDeptName());
            tree.setOrderNum(dept.getOrderNum());
            trees.add(tree);
        });
    }

    private void delete(List<String> deptIds) {
        removeByIds(deptIds);

        // 删除user-dept 关联关系
        userDataPermissionService.deleteByDeptIds(deptIds);
        // 递归删除所有对应子部门
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Dept::getParentId, deptIds);
        List<Dept> depts = deptMapper.selectList(queryWrapper);
        if (!CollectionUtils.isEmpty(depts)) {
            List<String> deptIdList = Lists.newArrayList();
            depts.forEach(d -> deptIdList.add(String.valueOf(d.getId())));
            this.delete(deptIdList);
        }

    }
}
