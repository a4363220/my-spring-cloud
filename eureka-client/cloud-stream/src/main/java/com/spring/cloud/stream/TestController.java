package com.spring.cloud.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/1/15 11:21
 */
@RestController
public class TestController {

    @Autowired
    SpringBootProducer springBootProducer;

    @Autowired
    SpringCloudProducer springCloudProducer;

    @GetMapping("/testSend")
    public String testSend(@RequestParam("message") String message) {
        //springBootProducer.sendTopic(message);
        //springBootProducer.sendMyTopic("my : "+message);
        springCloudProducer.send(message);
        springCloudProducer.sendMyTest("my ："+message);
        return "无";
    }
}
