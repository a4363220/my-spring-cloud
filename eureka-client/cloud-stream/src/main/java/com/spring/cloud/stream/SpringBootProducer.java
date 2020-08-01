package com.spring.cloud.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *     SpringBoot方式的发送者
 * @author 小谷
 * @Date 2020/1/15 11:15
 */
@Component
public class SpringBootProducer {

    // 依靠注入的方式注入模板
    private final KafkaTemplate<Integer,String> kafkaTemplate;

    // test topic
    private final String topic = "test";
    // myTest topic
    private final String myTopic = "myTest";

    @Autowired
    public SpringBootProducer(KafkaTemplate kafkaTemplate){
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
