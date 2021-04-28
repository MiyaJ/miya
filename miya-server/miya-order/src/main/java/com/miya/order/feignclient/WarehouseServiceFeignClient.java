package com.miya.order.feignclient;

import com.miya.entity.MiyaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Caixiaowei
 * @ClassName WarehouseServiceFeignClient
 * @Description
 * @createTime 2021/4/28 17:01
 */
@FeignClient("miya-warehouse")
public interface WarehouseServiceFeignClient {

    @GetMapping("/stock")
    public MiyaResponse getStock(@RequestParam("skuId") Long skuId);
}
