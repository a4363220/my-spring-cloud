package com.spring.cloud.stream;

import com.spring.cloud.zidingyistream.MessageConsumerSource;
import com.spring.cloud.zidingyistream.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 描述：
 *     spring-cloud-consumer 消费者
 * @author 小谷
 * @Date 2020/1/15 14:36
 */
@Component
@EnableBinding({Sink.class, MessageConsumerSource.class})
public class SpringCloudConsumer {

    @Autowired
    @Qualifier(Sink.INPUT)// bean 名称
    private SubscribableChannel subscribableChannel;

    @Autowired
    private Sink sink;

    // 自定义管道
    @Autowired
    @Qualifier(MessageConsumerSource.INTPUT)
    private MessageChannel myTopicMessage;

    // 当字段注入完成后的回调
//    @PostConstruct
//    public void init(){
//        // 实现异步回调
//        subscribableChannel.subscribe(new MessageHandler() {
//            @Override
//            public void handleMessage(Message<?> message) throws MessagingException {
//                System.out.println("cloud接收到消息："+message);
//            }
//        });
//    }

    //通过@ServiceActivator
//    @ServiceActivator(inputChannel = Sink.INPUT)
//    public void onMessage(Object message) {
//        System.out.println("@ServiceActivator : " + message);
//    }

    @StreamListener(value = Sink.INPUT)
    public void onMessage(String message){
        System.out.println("@StreamListener : " + message);
    }

    @StreamListener(value = MessageConsumerSource.INTPUT)
    public void onMessage1(String message){
        System.out.println("@StreamListener1 : my :"+message);
    }
}
