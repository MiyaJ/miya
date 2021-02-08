package com.miya.pay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.miya.pay.model.AlipayDTO;
import com.miya.pay.properties.AlipayProperties;
import com.miya.pay.service.IAlipayService;
import com.miya.utils.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author Caixiaowei
 * @ClassName AlipayServiceImpl
 * @Description
 * @createTime 2021/2/8 15:39
 */
@Slf4j
@Service
public class AlipayServiceImpl implements IAlipayService {

    /**
     * 订单有效期: 交易多长时间后关闭
     */
    private static final String EXPIRE = "30m";

    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private AlipayProperties alipayProperties;


    /**
     * 电脑网站支付
     *
     * @param alipayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    @Override
    public void pcPay(AlipayDTO alipayDTO) {
        String orderNo = alipayDTO.getOrderNo();
        BigDecimal amount = alipayDTO.getAmount();
        String desc = alipayDTO.getDesc();

        // 构建支付内容参数
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(orderNo);
        model.setTotalAmount(amount.toString());
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        model.setSubject("支付宝电脑网站支付");
        model.setBody(desc);
        model.setTimeoutExpress(EXPIRE);

        // 构建请求
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        request.setBizModel(model);
        request.setReturnUrl(alipayProperties.getReturnUrl());
        request.setNotifyUrl(alipayProperties.getNotifyUrl());

        // 发起支付请求
        AlipayTradePayResponse alipayResponse = null;
        try {
            alipayResponse = alipayClient.execute(request);

            if(alipayResponse.isSuccess()){
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }

            String form = alipayResponse.getBody();
            HttpServletResponse httpResponse = ServletUtil.getResponse();
            httpResponse.setContentType( "text/html;charset="  + alipayProperties.getCharset());
            //直接将完整的表单html输出到页面
            httpResponse.getWriter().write(form);
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();

        } catch (AlipayApiException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * app支付
     *
     * @param alipayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    @Override
    public void appPay(AlipayDTO alipayDTO) {

    }

    /**
     * 手机网站支付
     *
     * @param alipayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    @Override
    public void WapPay(AlipayDTO alipayDTO) {

    }

    /**
     * 条码 声波支付
     *
     * @param alipayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    @Override
    public void tradePay(AlipayDTO alipayDTO) {

    }

    /**
     * 扫码支付
     *
     * @param alipayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    @Override
    public void tradePrecreatePay(AlipayDTO alipayDTO) {

    }

    /**
     * 退款
     *
     * @param alipayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    @Override
    public void tradeRefund(AlipayDTO alipayDTO) {

    }

    /**
     * 支付交易查询
     *
     * @param alipayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    @Override
    public void tradeQuery(AlipayDTO alipayDTO) {

    }
}
