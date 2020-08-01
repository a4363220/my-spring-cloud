package com.spring.cloud.single.stream.boot;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/1/16 21:36
 */
@Component
public class KafkaBootConsumer {

    /**
     * 消费test topic
     * */
     //@KafkaListener(topics="${kafka.topic}")
    public void onMessage(String message){
        System.out.println("boot接收到（test）消息："+message);
    }

    /**
     * 消费myTest topic
     * */
    //@KafkaListener(topics = "${kafka.myTopic}")
    public void onMessageMy(String message){
        System.out.println("bootj接收到（myTest）消息："+message);
    }


}
