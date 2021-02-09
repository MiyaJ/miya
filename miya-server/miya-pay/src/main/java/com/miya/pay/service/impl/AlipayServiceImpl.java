package com.miya.pay.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayObject;
import com.alipay.api.AlipayRequest;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.miya.exception.PayException;
import com.miya.pay.model.AliTradePayDTO;
import com.miya.pay.model.AlipayDTO;
import com.miya.pay.model.AlipayRefundDTO;
import com.miya.pay.properties.AlipayProperties;
import com.miya.pay.service.IAlipayService;
import com.miya.utils.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        log.info("===========电脑网站支付============");

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
        AlipayTradePagePayRequest request = (AlipayTradePagePayRequest) buildAlipayRequest(model);

        // 发起支付请求
        AlipayTradePagePayResponse alipayResponse = null;
        try {
            alipayResponse = alipayClient.pageExecute(request);

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
    public String appPay(AlipayDTO alipayDTO) {
        log.info("===========app支付============");

        // 设置支付参数
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setOutTradeNo(alipayDTO.getOrderNo());
        model.setSubject("支付宝app支付");
        model.setTotalAmount(alipayDTO.getAmount().toPlainString());
        model.setBody(alipayDTO.getDesc());
        model.setProductCode("QUICK_MSECURITY_PAY");
        model.setTimeExpire(EXPIRE);
        model.setPassbackParams("callback params");

        // 构建请求
        AlipayTradeAppPayRequest request = (AlipayTradeAppPayRequest) buildAlipayRequest(model);
        AlipayTradeAppPayResponse payResponse = null;
        try {
            payResponse = alipayClient.sdkExecute(request);
            log.info("ali app pay success {}", payResponse.getBody());
            return payResponse.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new PayException("支付宝app支付失败");
        }
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
    public void wapPay(AlipayDTO alipayDTO) {
        log.info("===========支付宝手机网站支付============");
        // 设置支付参数
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(alipayDTO.getOrderNo());
        model.setSubject("支付宝app支付");
        model.setTotalAmount(alipayDTO.getAmount().toPlainString());
        model.setBody(alipayDTO.getDesc());
        model.setProductCode("QUICK_WAP_PAY");
        model.setTimeExpire(EXPIRE);

        // 构建请求
        AlipayTradeWapPayRequest request = (AlipayTradeWapPayRequest) buildAlipayRequest(model);
        AlipayTradeWapPayResponse payResponse = null;
        try {
            payResponse = alipayClient.pageExecute(request);
            String form = payResponse.getBody();
            HttpServletResponse response = ServletUtil.getResponse();
            response.setContentType("text/html;charset=" + alipayProperties.getCharset());
            PrintWriter out = response.getWriter();
            out.write(form);
            out.flush();
            out.close();
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new PayException("支付宝手机网站支付失败");
        } catch (IOException e) {
            e.printStackTrace();
            throw new PayException("支付宝response对象获取流失败");
        }

    }

    /**
     * 条码 声波支付
     *
     * @param aliTradePayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    @Override
    public String tradePay(AliTradePayDTO aliTradePayDTO) {
        log.info("===========支付宝条码 声波支付============");
        String subject = StrUtil.EMPTY;
        // 设置支付参数
        String scene = aliTradePayDTO.getScene();
        if ("wave_code".equals(scene)) {
            subject = "支付宝声波支付";
        } else if ("bar_code".equals(scene)) {
            subject = "支付宝条形码支付";
        } else {
            throw new PayException("入参错误");
        }
        AlipayTradePayModel model = new AlipayTradePayModel();
        model.setOutTradeNo(aliTradePayDTO.getOrderNo());
        model.setScene(scene);
        model.setAuthCode(aliTradePayDTO.getAuthCode());
        model.setSubject(subject);
        model.setStoreId(aliTradePayDTO.getStoreId());
        model.setTotalAmount(aliTradePayDTO.getAmount().toPlainString());
        model.setTimeoutExpress(EXPIRE);

        // 构建请求
        AlipayTradePayRequest request = (AlipayTradePayRequest) buildAlipayRequest(model);
        try {
            AlipayTradePayResponse payResponse = alipayClient.execute(request);
            log.info("调用支付结果: {}", JSONObject.toJSONString(payResponse));
            return payResponse.getBody();
        } catch (AlipayApiException e) {
            log.error("调用支付异常, error: {}", e);
            throw new PayException("支付宝条码或声波支付失败");
        }
    }

    /**
     * 扫码支付
     *
     * @param aliTradePayDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    @Override
    public String tradePrecreatePay(AliTradePayDTO aliTradePayDTO) {
        log.info("===========扫码支付============");
        // 设置支付参数
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setOutTradeNo(aliTradePayDTO.getOrderNo());
        model.setSubject("扫码支付");
        model.setStoreId(aliTradePayDTO.getStoreId());
        model.setTotalAmount(aliTradePayDTO.getAmount().toPlainString());
        model.setTimeoutExpress(EXPIRE);

        // 构建请求
        AlipayTradePrecreateRequest request = (AlipayTradePrecreateRequest) buildAlipayRequest(model);
        try {
            AlipayTradePrecreateResponse payResponse = alipayClient.execute(request);
            log.info("扫码支付结果: {}", JSONObject.toJSONString(payResponse));
            String body = payResponse.getBody();
            JSONObject jsonObject = JSONObject.parseObject(body);
            String precreateResponse = jsonObject.getString("alipay_trade_precreate_response");
            JSONObject json = JSONObject.parseObject(precreateResponse);
            String code = json.getString("code");
            if ("10000".equalsIgnoreCase(code)) {
                return json.getString("qr_code");
            }
            return json.getString("sub_msg");
        } catch (AlipayApiException e) {
            log.error("扫码支付异常, orderNo: {}, error :{}", aliTradePayDTO.getOrderNo(), e);
            throw new PayException("扫码支付失败");
        }
    }

    /**
     * 退款
     *
     * @param alipayRefundDTO 支付宝退款参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    @Override
    public void tradeRefund(AlipayRefundDTO alipayRefundDTO) {
        log.info("===========支付宝退款============");
        // 设置退款参数
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(alipayRefundDTO.getOrderNo());
        model.setTradeNo(alipayRefundDTO.getTradeNo());
        model.setRefundAmount(alipayRefundDTO.getAmount().toPlainString());
        model.setRefundReason(alipayRefundDTO.getDesc());
        if (AlipayRefundDTO.TYPE_PART.equals(alipayRefundDTO.getType())) {
            model.setOutRequestNo(IdUtil.randomUUID());
        }
        // 构建请求
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizModel(model);
        try {
            AlipayTradeRefundResponse refundResponse = alipayClient.execute(request);
            log.info("支付宝退款结果: {}", JSONObject.toJSONString(refundResponse));
        } catch (AlipayApiException e) {
            log.error("支付宝退款异常, 订单号:{}, error:{}", alipayRefundDTO.getOrderNo(), e);
            throw new PayException("支付宝退款异常");
        }

    }

    /**
     * 支付交易查询
     *
     * @param alipayRefundDTO 支付参数
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 15:44
     */
    @Override
    public String tradeQuery(AlipayRefundDTO alipayRefundDTO) {
        log.info("===========支付交易查询============");
        // 设置查询参数
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(alipayRefundDTO.getOrderNo());
        model.setTradeNo(alipayRefundDTO.getTradeNo());

        // 构建请求
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizModel(model);
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
            log.info("交易订单查询结果: {}", JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                log.info("成功信息 : {}",response.getBody());
                return response.getBody();
            } else {
                log.error("失败信息 : {}",response.getBody());
                throw new PayException(response.getSubMsg());
            }

        } catch (AlipayApiException e) {
            log.error("交易订单查询异常, 订单号: {}, 支付交易号: {}", alipayRefundDTO.getOrderNo(), alipayRefundDTO.getTradeNo());
            throw new PayException("交易订单查询异常");
        }
    }

    /**
     * 构建支付宝支付请求
     *
     * @param model 支付参数
     * @return AlipayTradePayRequest 支付请求
     * @author Caixiaowei
     * @updateTime 2021/2/9 14:53
     */
    private AlipayRequest buildAlipayRequest(AlipayObject model) {
        AlipayRequest request = new AlipayTradePayRequest();
        request.setNotifyUrl(alipayProperties.getNotifyUrl());
        request.setReturnUrl(alipayProperties.getReturnUrl());
        request.setBizModel(model);
        return request;
    }

}
