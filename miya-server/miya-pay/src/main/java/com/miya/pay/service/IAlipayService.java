package com.miya.pay.service;

import com.miya.pay.model.AlipayDTO;

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
    void appPay(AlipayDTO alipayDTO);

    /**
     * 手机网站支付
     *
     * @param alipayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    void WapPay(AlipayDTO alipayDTO);

    /**
     * 条码 声波支付
     *
     * @param alipayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    void tradePay(AlipayDTO alipayDTO);

    /**
     * 扫码支付
     *
     * @param alipayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    void tradePrecreatePay(AlipayDTO alipayDTO);

    /**
     * 退款
     *
     * @param alipayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    void tradeRefund(AlipayDTO alipayDTO);

    /**
     * 支付交易查询
     *
     * @param alipayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    void tradeQuery(AlipayDTO alipayDTO);

}
