package com.miya.pay.ali;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.miya.pay.properties.AliPayProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Caixiaowei
 * @ClassName AliPayConfigure
 * @Description
 * @createTime 2021/2/7 16:03
 */
@Configuration
public class AliPayConfigure {

    @Bean
    public AlipayClient alipayClient(AliPayProperties aliPayProperties) throws AlipayApiException {
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();

        return new DefaultAlipayClient(certAlipayRequest);
    }
}
