package com.spring.cloud.stream;

import com.spring.cloud.zidingyistream.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *     spring-cloud-stream
 * @author 小谷
 * @Date 2020/1/15 12:07
 */
@Component
@EnableBinding({Source.class,MessageSource.class})// 激活发送端
public class SpringCloudProducer {

    @Autowired
    @Qualifier(Source.OUTPUT)// Bean名称
    private MessageChannel messageChannel;

    @Autowired
    private Source source;


    @Autowired
    @Qualifier(MessageSource.OUTPUT)
    private MessageChannel myTopicChannel;

    /**
     * 发送消息
     * */
    public void send(String message){
        //messageChannel.send(MessageBuilder.withPayload(message.toString()).build());
        source.output().send(MessageBuilder.withPayload(message).build());

    }

    /**
     * 发送消息
     * */
    public void sendMyTest(String message){
        myTopicChannel.send(MessageBuilder.withPayload(message).build());
    }
}
