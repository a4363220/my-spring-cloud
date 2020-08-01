package com.spring.cloud.eureka.client.merchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/1/12 15:39
 */
@SpringBootApplication
@EnableDiscoveryClient // 激活客户端eureka
@EnableHystrix// 激活hystrix
public class MerchantServiceProviderBootStrap {

    public static void main(String[] args) {
        SpringApplication.run(MerchantServiceProviderBootStrap.class,args);
    }
}
