package com.miya.pay.service;

import com.miya.pay.model.AliTradePayDTO;
import com.miya.pay.model.AlipayDTO;
import com.miya.pay.model.AlipayRefundDTO;

/**
 * @author Caixiaowei
 * @ClassName IAlipayService
 * @Description
 * @createTime 2021/2/8 15:38
 */
public interface IAlipayService {

    /**
     * 电脑网站支付
     *
     * @param alipayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    void pcPay(AlipayDTO alipayDTO);

    /**
     * app支付
     *
     * @param alipayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    String appPay(AlipayDTO alipayDTO);

    /**
     * 手机网站支付
     *
     * @param alipayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    void wapPay(AlipayDTO alipayDTO);

    /**
     * 条码 声波支付
     *
     * @param aliTradePayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    String tradePay(AliTradePayDTO aliTradePayDTO);

    /**
     * 扫码支付
     *
     * @param aliTradePayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    String tradePrecreatePay(AliTradePayDTO aliTradePayDTO);

    /**
     * 退款
     *
     * @param alipayRefundDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    void tradeRefund(AlipayRefundDTO alipayRefundDTO);

    /**
     * 支付交易查询
     *
     * @param alipayRefundDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    String tradeQuery(AlipayRefundDTO alipayRefundDTO);

}
