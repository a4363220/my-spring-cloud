package com.spring.cloud.single.stream.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 描述：
 * kafka cloud 版本发送者
 * <p>
 * 通过消息管道的方式
 *
 * @author 小谷
 * @Date 2020/1/16 22:08
 */
@Component
@EnableBinding({Source.class,CustomMessage.class}) // 激活发送端
public class KafkaCloudProducer {

    // test topic 使用原生的方式
    @Autowired
    @Qualifier(Source.OUTPUT)
    private MessageChannel messageChannel;

    // myTest topic 自定义管道
    @Autowired
    @Qualifier(CustomMessage.OUTPUT)
    private MessageChannel messageChannel1;

    /**
     * 发送消息 test
     * */
     public void sendTest(String message){
          messageChannel.send(MessageBuilder.withPayload(message).build());
     }

     /**
      * 发送消息 myTest
      * */
     public void sendMyTest(String message){
         messageChannel1.send(MessageBuilder.withPayload(message).build());
     }
}
