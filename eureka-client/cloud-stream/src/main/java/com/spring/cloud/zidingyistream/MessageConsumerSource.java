package com.spring.cloud.zidingyistream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 描述：
 *     自定义消息接收者
 * @author 小谷
 * @Date 2020/1/15 15:51
 */
public interface MessageConsumerSource {

    String INTPUT = "myTest";

    @Input(INTPUT)
    SubscribableChannel myTest1();
}
