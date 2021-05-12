package com.miya.order.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Caixiaowei
 * @ClassName OrderCreateParam
 * @Description
 * @createTime 2021/5/8 11:38
 */
@Data
public class OrderCreateParam implements Serializable {
    private static final long serialVersionUID = 7978806042174025126L;

    /**
     * 商品skuid
     */
    private Long skuId;

    /**
     * 商品数量
     */
    private Integer count;

}
