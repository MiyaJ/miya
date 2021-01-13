//package com.miya.system.configure;
//
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author Caixiaowei
// * @ClassName MQProducerConfigure
// * @Description mq producer 配置
// * @createTime 2020/10/23 13:52
// */
//@Slf4j
//@Data
//@Configuration
//@ConfigurationProperties(prefix = "rocketmq.producer")
//public class MQProducerConfigure {
//
//    private String groupName;
//    private String namesrvAddr;
//    /**
//     * 消息最大值
//     */
//    private Integer maxMessageSize;
//    /**
//     * 消息发送超时时间
//     */
//    private Integer sendMsgTimeOut;
//    /**
//     * 失败重试次数
//     */
//    private Integer retryTimesWhenSendFailed;
//
//    /**
//     * mq 生成者配置
//     * @return
//     * @throws MQClientException
//     */
//    @Bean
//    @ConditionalOnProperty(prefix = "rocketmq.producer", value = "isOnOff", havingValue = "on")
//    public DefaultMQProducer defaultProducer() throws MQClientException {
//        log.info("defaultProducer 正在创建---------------------------------------");
//        DefaultMQProducer producer = new DefaultMQProducer(groupName);
//        producer.setNamesrvAddr(namesrvAddr);
//        producer.setVipChannelEnabled(false);
//        producer.setMaxMessageSize(maxMessageSize);
//        producer.setSendMsgTimeout(sendMsgTimeOut);
//        producer.setRetryTimesWhenSendAsyncFailed(retryTimesWhenSendFailed);
//        producer.start();
//        log.info("rocketmq producer server 开启成功----------------------------------");
//        return producer;
//    }
//
//}
