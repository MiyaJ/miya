package com.miya.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.miya.entity.MiyaResponse;
import com.miya.entity.QueryRequest;
import com.miya.entity.system.SystemUser;
import com.miya.system.service.IUserService;
import com.miya.utils.MiyaUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Caixiaowei
 * @ClassName UserController.java
 * @Description TODO
 * @createTime 2020年05月14日 10:18:00
 */
@RestController
@RequestMapping("/user")
@Api(tags = "系统用户管理")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * @title 分页查找用户信息
     * @description
     * @author Caixiaowei 
     * @updateTime 2020/5/14 10:21 
     * @throws 
     */
    @GetMapping("/findUserDetailPage")
    public MiyaResponse findUserDetailPage(QueryRequest request, SystemUser user) {
        IPage<SystemUser> userDetailPage = userService.findUserDetailPage(user, request);
        return MiyaResponse.success(MiyaUtil.getDataTable(userDetailPage));
    }

}
