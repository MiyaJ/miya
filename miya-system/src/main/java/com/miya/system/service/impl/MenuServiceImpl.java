package com.miya.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miya.entity.system.Menu;
import com.miya.system.mapper.MenuMapper;
import com.miya.system.service.IMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-05-14
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
