package com.spring.cloud.single.stream.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *     kafka cloud 版本消费者
 * @author 小谷
 * @Date 2020/1/16 22:08
 */
@Component
@EnableBinding({Sink.class,CustomMessage.class})//激活消息绑定
public class KafkaCloudConsumer {

    @Autowired
    @Qualifier(Sink.INPUT)// bean 名称
    private SubscribableChannel subscribableChannel;

    @Autowired
    private Sink sink;

    @Autowired
    @Qualifier(CustomMessage.INPUT)
    private MessageChannel subscribableChannel1;


    @StreamListener(value = Sink.INPUT)
    public void onMessage(String message){
        System.out.println("cloud接收到（test）消息: " + message);
    }

    @StreamListener(value = CustomMessage.INPUT)
    public void onMessage1(String message){
        System.out.println("cloud接收到（myTest）消息: " + message);
    }
}
