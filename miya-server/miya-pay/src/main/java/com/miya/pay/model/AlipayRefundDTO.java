package com.miya.pay.model;

import lombok.Data;

/**
 * @author Caixiaowei
 * @ClassName AlipayRefundVO
 * @Description 支付宝退款参数
 * @createTime 2021/2/9 15:43
 */
@Data
public class AlipayRefundDTO extends AlipayDTO {

    /**
     * 全部退款
     */
    public static Integer TYPE_ALL = 0;
    /**
     * 部分退款
     */
    public static Integer TYPE_PART = 1;


    /**
     * 支付宝交易号
     */
    private String tradeNo;

    /**
     * 0 : 全额退款  1 : 部分退款
     */
    private Integer type;
}
