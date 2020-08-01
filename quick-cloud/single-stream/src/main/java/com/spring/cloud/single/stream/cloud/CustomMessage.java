package com.spring.cloud.single.stream.cloud;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 描述：
 * 自定义消息管道
 *
 * @author 小谷
 * @Date 2020/1/16 22:43
 */
public interface CustomMessage {

    /**
     * 消息来源的管道名称 myTest
     */
    String OUTPUT = "myTest";

    @Output(OUTPUT)
    MessageChannel myTest();

    /**
     * 消息接收注册 myTest
     */
    String INPUT = "myTest";

    @Input(INPUT)
    SubscribableChannel myTest1();
}
