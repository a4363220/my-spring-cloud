package com.spring.cloud.eureka.client.merchant;

import com.spring.cloud.eureka.client.merchant.api.service.PersonService;
import com.spring.cloud.eureka.client.merchant.consumer.controller.PersonRestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/1/12 17:18
 */
@SpringBootApplication
@EnableDiscoveryClient // 激活eureka
@EnableHystrix// 激活熔断机制
@EnableFeignClients(clients = PersonService.class)
public class MerchantServiceConsumerBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(MerchantServiceConsumerBootstrap.class, args);
    }
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
