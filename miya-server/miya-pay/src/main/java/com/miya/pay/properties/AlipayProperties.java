package com.miya.pay.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Caixiaowei
 * @ClassName AliProperties
 * @Description
 * @createTime 2021/2/7 15:57
 */
@Data
@SpringBootConfiguration
@PropertySource(value = "classpath:pay.properties")
@ConfigurationProperties(prefix = "ali")
public class AlipayProperties {

    /**
     * 支付宝网关（固定）
     */
    private String url;

    /**
     * APPID 即创建应用后生成
     */
    private String appId;

    /**
     * 开发者应用私钥，由开发者自己生成
     */
    private String appPrivateKey;

    /**
     * 参数返回格式，只支持 json 格式
     */
    private String format;

    /**
     * 请求和签名使用的字符编码格式，支持 GBK 和 UTF-8
     */
    private String charset;

    /**
     * 支付宝公钥，由支付宝生成
     */
    private String alipayPublicKey;

    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持 RSA2 和 RSA，推荐商家使用 RSA2
     */
    private String signType;

    /**
     * 支付响应url
     */
    private String returnUrl;

    /**
     * 异步通知url
     */
    private String notifyUrl;

    /************************** 商家版 秘钥证书配置 **************************/
    /**
     * 开发者应用私钥路径
     */
    private String appPrivateKeyPath;

    /**
     * 支付宝公钥
     */
    private String appCertPublicKeyPath;

    /**
     * alipay public cert path
     */
    private String alipayPublicCertPath;
    /**
     * alipay root cert path
     */
    private String alipayRootCertPath;
}
