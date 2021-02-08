package com.miya.pay.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Caixiaowei
 * @ClassName AlipayDTO
 * @Description
 * @createTime 2021/2/8 15:40
 */
@Data
public class AlipayDTO implements Serializable {
    private static final long serialVersionUID = -5069262545055585119L;

    /**
     * 支付金额
     */
    private BigDecimal amount;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 订单描述
     */
    private String desc;

}
