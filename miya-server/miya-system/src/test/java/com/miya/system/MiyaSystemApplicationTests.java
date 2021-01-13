package com.miya.system;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MiyaSystemApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    DefaultMQProducer defaultMQProducer;

    @Test
    public void test_rocketmq() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message();
        message.setTopic("MyTopic");
        message.setTags("TestTag");
        message.setBody("hello rocketmq".getBytes());

        SendResult send = defaultMQProducer.send(message);
        System.out.println(send);

    }
}
