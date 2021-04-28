package com.miya.pay.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Caixiaowei
 * @ClassName AliTradePayDTO
 * @Description 支付宝交易参数
 * @createTime 2021/2/9 15:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AliTradePayDTO extends AlipayDTO {

    /**
     * 授权码
     */
    private String authCode;

    /**
     * 支付场景，条码：bar_code，声波：wave_code
     */
    private String scene;

    /**
     * 商户门店编号
     */
    private String storeId;
}
