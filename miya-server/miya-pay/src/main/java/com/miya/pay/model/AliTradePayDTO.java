package com.miya.pay.model;

import lombok.Data;

/**
 * @author Caixiaowei
 * @ClassName AliTradePayDTO
 * @Description
 * @createTime 2021/2/9 15:27
 */
@Data
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