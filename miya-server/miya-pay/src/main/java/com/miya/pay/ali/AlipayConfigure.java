package com.miya.pay.ali;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.miya.exception.PayException;
import com.miya.pay.properties.AlipayProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Caixiaowei
 * @ClassName AliPayConfigure
 * @Description
 * @createTime 2021/2/7 16:03
 */
@Configuration
@Slf4j
public class AlipayConfigure {

    @Autowired
    private AlipayProperties aliPayProperties;

    /**
     * 商家公钥证书客户端
     *
     * @param
     * @return
     * @author Caixiaowei
     * @updateTime 2021/2/8 14:50
     */
//    @Bean
//    public AlipayClient alipayClient() throws AlipayApiException {
//        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
//
//        certAlipayRequest.setServerUrl(aliPayProperties.getUrl());
//        certAlipayRequest.setAppId(aliPayProperties.getAppId());
//        certAlipayRequest.setCharset(aliPayProperties.getCharset());
//        certAlipayRequest.setFormat(aliPayProperties.getFormat());
//        certAlipayRequest.setSignType(aliPayProperties.getSignType());
//        certAlipayRequest.setPrivateKey(appRSAPrivateKey(aliPayProperties.getAppPrivateKeyPath()));
//        certAlipayRequest.setCertPath(getFileAbsolutePath(aliPayProperties.getAppCertPublicKeyPath()));
//        certAlipayRequest.setAlipayPublicCertPath(getFileAbsolutePath(aliPayProperties.getAlipayPublicCertPath()));
//        certAlipayRequest.setRootCertPath(getFileAbsolutePath(aliPayProperties.getAlipayRootCertPath()));
//
//        return new DefaultAlipayClient(certAlipayRequest);
//    }

    /**
     *
     * @return
     */
    @Bean
    public AlipayClient alipayClient() {
        return new DefaultAlipayClient(
                aliPayProperties.getUrl(),
                aliPayProperties.getAppId(),
                aliPayProperties.getAppPrivateKey(),
                aliPayProperties.getFormat(),
                aliPayProperties.getCharset(),
                aliPayProperties.getAlipayPublicKey(),
                aliPayProperties.getSignType());
    }

    private String getFileAbsolutePath(String classPath) {
        try {
            return new ClassPathResource(classPath).getFile().getAbsolutePath();
        } catch (IOException e) {
            log.error("ali pay cert path is not exist ,{}", e.getMessage());
            throw new PayException("ali pay cert path is not exist");
        }

    }


    private String appRSAPrivateKey(String classPath) {
        ClassPathResource resource = new ClassPathResource(classPath);
        try {
            FileReader in = new FileReader(resource.getFile());
            try(BufferedReader bufferedReader = new BufferedReader(in)){
                return bufferedReader.readLine();
            }
        } catch (IOException e) {
            log.error("ali pay app private key is required ,{}", e.getMessage());
            throw new PayException("ali pay app private key is required");
        }
    }
}
