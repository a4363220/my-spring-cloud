package com.spring.cloud.single.stream.controller;

import com.spring.cloud.single.stream.boot.KafkaBootProducer;
import com.spring.cloud.single.stream.cloud.KafkaCloudProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *     测试kafka发送消息
 * @author 小谷
 * @Date 2020/1/16 21:40
 */
@RestController
public class TestSendController {

    @Autowired
    private KafkaBootProducer kafkaBootProducer;

    @Autowired
    private KafkaCloudProducer kafkaCloudProducer;

    @GetMapping("/testSend")
    public String testSend(String message){
        System.out.println("进入发送者测试");
        //kafkaBootProducer.sendTopic(message);
        //kafkaBootProducer.sendMyTopic(message);
        kafkaCloudProducer.sendTest(message);
        kafkaCloudProducer.sendMyTest(message);
        return "测试发送";
    }
}
