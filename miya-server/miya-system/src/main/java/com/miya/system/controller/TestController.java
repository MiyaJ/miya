package com.miya.system.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Caixiaowei
 * @ClassName TestController.java
 * @Description 测试restful
 * @createTime 2020年05月12日 16:44:00
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @GetMapping("info")
    public String test(){
        return "miya-system";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("/sendMQ")
    public Object sendMQ(String topic) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message(topic, "hello rocketmq".getBytes());
        SendResult result = defaultMQProducer.send(message);
        log.info("发送消息响应: {}", JSONObject.toJSON(result));
        return result;
    }
}
