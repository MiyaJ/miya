package com.miya.order.controller;

import com.miya.entity.MiyaResponse;
import com.miya.order.feignclient.WarehouseServiceFeignClient;
import com.miya.order.model.OrderCreateParam;
import com.miya.order.service.IOmsOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Caixiaowei
 * @ClassName OrderController
 * @Description
 * @createTime 2021/4/28 17:02
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private IOmsOrderService orderService;

    @Autowired
    private WarehouseServiceFeignClient warehouseServiceFeignClient;

    @GetMapping("/getStock")
    public MiyaResponse getStock(Long skuId) {
        log.info(("订单服务--->调用 warehouseServiceFeignClient"));
        return warehouseServiceFeignClient.getStock(skuId);
    }
    @GetMapping("/test")
    public String test() {
        log.info(("订单服务--->调用 warehouseServiceFeignClient"));
        return warehouseServiceFeignClient.test();
    }

    @GetMapping("/hello")
    public String hello() {
        return "success";
    }


    /**
     * 创建订单
     *
     * @param
     * @return
     * @author Caixiaowei
     * @updateTime 2021/5/8 11:36
     */
    @PostMapping("/create")
    public MiyaResponse create(@RequestBody OrderCreateParam orderCreateParam) {

        return orderService.create(orderCreateParam);
    }
}
