package com.miya.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miya.entity.system.Dept;
import com.miya.system.mapper.DeptMapper;
import com.miya.system.service.IDeptService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-14
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

}
