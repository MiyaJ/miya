package com.miya.warehouse.controller;

import com.miya.entity.MiyaResponse;
import com.miya.warehouse.model.Stock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Caixiaowei
 * @ClassName WarehouceController
 * @Description
 * @createTime 2021/4/28 15:14
 */
@RestController
@RequestMapping("/stock")
public class WarehouseController {


    @GetMapping("/getStock")
    public MiyaResponse getStock(Long skuId) {
        Stock stock = null;
        if (skuId == 11011) {
            //模拟有库存商品
            stock = new Stock(1101L, "Apple iPhone 11 128GB 紫色", 32, "台", "Apple 11 紫色版对应商品描述");
        } else if (skuId == 11021) {
            //模拟无库存商品
            stock = new Stock(1101L, "Apple iPhone 11 256GB 白色", 0, "台", "Apple 11 白色版对应商品描述");
        } else {
            return MiyaResponse.error("查无此商品");
        }

        return MiyaResponse.success(stock);
    }

    @GetMapping("/test")
    public String test() {
        return "调用成功!";
    }
}
