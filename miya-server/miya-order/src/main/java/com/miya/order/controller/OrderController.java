package com.miya.order.controller;

import com.miya.entity.MiyaResponse;
import com.miya.order.feignclient.WarehouseServiceFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
