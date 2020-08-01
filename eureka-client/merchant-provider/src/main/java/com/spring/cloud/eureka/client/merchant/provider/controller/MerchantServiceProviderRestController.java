package com.spring.cloud.eureka.client.merchant.provider.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
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
 * @Date 2020/1/12 15:43
 */
@RestController
public class MerchantServiceProviderRestController {

    @Autowired
    MerchantService merchantService;

    @PostMapping("/merchant/add")
    public Merchant add(@RequestBody Merchant merchant) {
        System.out.println("provider入参：" + merchant);
        return merchantService.addMerchant(merchant);
    }

    @GetMapping("/merchant/queryAll")
    public List<Merchant> queryAll() {
        return merchantService.queryMerchantAll();
    }



}
