package com.miya.pay.callback;

import cn.hutool.extra.servlet.ServletUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.miya.pay.properties.AlipayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Caixiaowei
 * @ClassName AlipayCallback
 * @Description
 * @createTime 2021/2/8 16:48
 */
@RequestMapping("/callback")
public class AlipayCallback {

    @Autowired
    protected AlipayProperties alipayProperties;

    /**
     * 支付成功跳转
     *
     * @param
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 16:59
     */
    @RequestMapping("/returnUrl")
    public void returnUrl(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
        Boolean signVerified = this.rsaCheckV1(request);
        if (signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
        } else {
            // TODO 验签失败则记录异常日志，并在response中返回failure.
        }
    }


    /**
     * 异步返回结果通知
     *
     * @param
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 16:59
     */
    @RequestMapping("/notifyUrl")
    public void notifyUrl(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
        Boolean signVerified = this.rsaCheckV1(request);
        if (signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
        } else {
            // TODO 验签失败则记录异常日志，并在response中返回failure.
        }
    }

    /**
     * 异步返回结果进行验签
     *
     * @param request
     * @return 验签结果 true|false
     * @author Caixiaowei
     * @updateTime 2021/2/8 16:57
     */
    private Boolean rsaCheckV1(HttpServletRequest request) throws AlipayApiException {
        Map<String, String> paramMap = ServletUtil.getParamMap(request);
        boolean checkV1 = AlipaySignature.rsaCheckV1(paramMap, alipayProperties.getAlipayPublicKey(), alipayProperties.getCharset(), alipayProperties.getSignType());
        return checkV1;
    }
}
