package com.miya.order.feignclient;

import com.miya.entity.MiyaResponse;
import com.miya.order.feignclient.fallback.WarehouseServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Caixiaowei
 * @ClassName WarehouseServiceFeignClient
 * @Description
 * @createTime 2021/4/28 17:01
 */
@Component
@FeignClient(value = "miya-warehouse", fallbackFactory = WarehouseServiceFallback.class)
public interface WarehouseServiceFeignClient {

    @GetMapping("/stock/getStock")
    MiyaResponse getStock(@RequestParam("skuId") Long skuId);

    @GetMapping("/stock/test")
    String test();
}
