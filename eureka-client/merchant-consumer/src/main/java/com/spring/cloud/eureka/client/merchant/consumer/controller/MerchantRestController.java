package com.spring.cloud.eureka.client.merchant.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spring.cloud.eureka.client.merchant.api.domain.Merchant;
import com.spring.cloud.eureka.client.merchant.api.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/1/12 17:20
 */
@RestController
public class MerchantRestController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping("/merchant/add")
    public Merchant add(@RequestBody Merchant merchant){
        return merchantService.addMerchant(merchant);
    }

    @GetMapping("/merchant/queryAll")
    public List<Merchant> queryAll(){
        return merchantService.queryMerchantAll();
    }

    // 使用一个随机数
    private static final Random random = new Random();

    @GetMapping("/merchant/testHystrix")
    @HystrixCommand(fallbackMethod = "errorContent",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")})
    public String testHystrix() throws InterruptedException {
        // 如果随机数大于100则熔断
        int value = random.nextInt(200);
        System.out.println("测试随机数："+value);
        Thread.sleep(value);

        return "test success";
    }

    public String errorContent(){
        return "test fail";
    }
}
