package com.spring.cloud.single.stream.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *    spring boot 发送者
 * @author 小谷
 * @Date 2020/1/16 21:35
 */
@Component
public class KafkaBootProducer {

    // 依靠注入的方式注入模板
    private final KafkaTemplate<Integer,String> kafkaTemplate;

    // test topic
    private final String topic = "test";
    // myTest topic
    private final String myTopic = "myTest";

    @Autowired
    public KafkaBootProducer(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;

    }

    /**
     * 发送：test topic
     * */
    public void sendTopic(String message){
        kafkaTemplate.send(topic,message);
    }

    /**
     * 发送：myTest topic
     * */
    public void sendMyTopic(String message){
        kafkaTemplate.send(myTopic,message);
    }

}
