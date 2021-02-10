package com.miya.pay.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.miya.pay.model.AliTradePayDTO;
import com.miya.pay.model.AlipayDTO;
import com.miya.pay.service.IAlipayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author Caixiaowei
 * @ClassName TestController
 * @Description
 * @createTime 2021/2/9 9:53
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private IAlipayService alipayService;

    @GetMapping("/test")
    public Object test(String param) {
        return param;
    }

    @GetMapping("/pcPay")
    public void pcPay() {
        AlipayDTO alipayDTO = new AlipayDTO();
        alipayDTO.setOrderNo(IdUtil.fastSimpleUUID());
        alipayDTO.setAmount(new BigDecimal("0.01"));
        alipayDTO.setDesc("电脑网站支付测试");
        try {
            alipayService.pcPay(alipayDTO);
            log.info("电脑网站支付测试: 成功");
        } catch (Exception e) {
            log.error("电脑网站支付测试: 失败, error: {}", e);
        }
    }

    /**
     * 条码 | 声波 支付
     *
     * @param authCode 授权码
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/10 11:32
     */
    @RequestMapping("/tradePay")
    public void tradePay(String authCode) {
        AliTradePayDTO aliTradePayDTO = new AliTradePayDTO();
        aliTradePayDTO.setOrderNo(IdUtil.fastSimpleUUID());
        aliTradePayDTO.setAmount(new BigDecimal("0.01"));
        aliTradePayDTO.setDesc("条码 | 声波 支付");
        aliTradePayDTO.setAuthCode(authCode);
        aliTradePayDTO.setScene("bar_code");
        aliTradePayDTO.setStoreId(StrUtil.EMPTY);
        alipayService.tradePay(aliTradePayDTO);
    }

    /**
     * 扫码支付
     *
     * @param authCode 授权码
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/10 11:32
     */
    @RequestMapping("/tradePrecreatePay")
    public void tradePrecreatePay(String authCode) {
        AliTradePayDTO aliTradePayDTO = new AliTradePayDTO();
        aliTradePayDTO.setOrderNo(IdUtil.fastSimpleUUID());
        aliTradePayDTO.setAmount(new BigDecimal("0.01"));
        aliTradePayDTO.setDesc("扫码支付");
        aliTradePayDTO.setAuthCode(authCode);
        aliTradePayDTO.setScene("bar_code");
        aliTradePayDTO.setStoreId(StrUtil.EMPTY);
        alipayService.tradePrecreatePay(aliTradePayDTO);
    }
}
