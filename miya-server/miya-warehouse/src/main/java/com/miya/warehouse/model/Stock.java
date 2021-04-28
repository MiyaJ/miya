package com.miya.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Caixiaowei
 * @ClassName Stock
 * @Description 库存
 * @createTime 2021/4/28 15:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock implements Serializable {

    private static final long serialVersionUID = -6293522790618740986L;

    /**
     * 商品品类编号
     */
    private Long skuId;

    /**
     * 商品与品类名称
     */
    private String title;

    /**
     * 库存数量
     */
    private Integer quantity;

    /**
     * 单位
     */
    private String unit;

    /**
     * 描述信息
     */
    private String description;
}
