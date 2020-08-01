package com.spring.cloud.animal.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * 描述：
 * Hstrix 熔断测试控制层
 *
 * @author 小谷
 * @Date 2020/1/16 16:59
 */
@RestController
public class HstrixController {


    /**
     * 测试超时
     */
    private final static Random random = new Random();

    @GetMapping("/testTimeOut")
    @HystrixCommand(fallbackMethod = "timeOutError",
            commandProperties = {
                    @HystrixProperty(
                            name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "100"
                    )
            })
    public String testTimeOut() throws InterruptedException {
        // 如果随机时间 大于 100 ，那么触发容错
        int value = random.nextInt(200);

        System.out.println("testTimeOut 当前毫秒 ："+value+" ms.");

        Thread.sleep(value);
        return "测试超时";
    }


    public String timeOutError(){
        return "超时熔断";
    }

}
