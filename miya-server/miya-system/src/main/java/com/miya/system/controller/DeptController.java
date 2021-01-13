package com.miya.system.controller;

import com.miya.constant.StringConstant;
import com.miya.entity.MiyaResponse;
import com.miya.entity.QueryRequest;
import com.miya.entity.system.Dept;
import com.miya.system.service.IDeptService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author caixiaowei
 * @version 1.0
 * @classname DeptController
 * @description
 * @date 2020/06/05 22:40
 */
@Api(value = "部门管理")
@RestController
@RequestMapping("/dept")
@Slf4j
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @GetMapping("/list")
    public MiyaResponse deptList(QueryRequest request, Dept dept) {
        Map<String, Object> result = deptService.deptList(dept, request);
        return MiyaResponse.success(result);
    }

    @PostMapping("/add")
    public MiyaResponse add(@RequestBody Dept dept) {
        deptService.add(dept);
        return MiyaResponse.success();
    }

    public MiyaResponse update(@RequestBody Dept dept) {
        deptService.update(dept);
        return MiyaResponse.success();
    }

    public MiyaResponse delete(String deptIds) {
        deptService.deleteDepts(deptIds.split(StringConstant.COMMA));
        return MiyaResponse.success();
    }

}
