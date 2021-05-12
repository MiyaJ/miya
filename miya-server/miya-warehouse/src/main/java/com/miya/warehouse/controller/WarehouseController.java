package com.miya.warehouse.controller;

import com.miya.entity.MiyaResponse;
import com.miya.entity.warehouse.PmsSkuStock;
import com.miya.warehouse.model.Stock;
import com.miya.warehouse.service.IPmsSkuStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Caixiaowei
 * @ClassName WarehouceController
 * @Description
 * @createTime 2021/4/28 15:14
 */
@RestController
@RequestMapping("/stock")
@Slf4j
public class WarehouseController {

    @Autowired
    private IPmsSkuStockService pmsSkuStockService;

    @GetMapping("/getStock")
    public MiyaResponse getStock(Long skuId, HttpServletRequest request) {
        String contentType = request.getContentType();
        log.info("contentType: {}", contentType);

        String method = request.getMethod();
        log.info("method: {}", method);

        PmsSkuStock skuStock = pmsSkuStockService.getById(skuId);
        return MiyaResponse.success(skuStock);
    }

    @GetMapping("/test")
    public String test() {
        return "调用成功!";
    }
}
