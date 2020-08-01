package com.spring.cloud.zidingyistream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 描述：
 *    自定义消息源
 * @author 小谷
 * @Date 2020/1/15 15:39
 */
public interface MessageSource {

    /**
     * 消息来源的管道名称  myTest
     * */
    String OUTPUT = "myTest";

    @Output(OUTPUT)
    MessageChannel myTest();
}
