package com.spring.cloud.stream;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *    Spring Boot 消费者
 * @author 小谷
 * @Date 2020/1/15 11:34
 */
@Component
public class SpringBootConsumer {

    /**
    * 消费test topic
    * */
   // @KafkaListener(topics="${kafka.topic}")
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
