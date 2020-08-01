package com.spring.cloud.animal;

import com.spring.cloud.animal.api.CatService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 描述：
 *     动物消费者启动类
 * @author 小谷
 * @Date 2020/1/16 16:29
 */
@SpringBootApplication
@EnableDiscoveryClient // 激活服务发现客户端
@EnableHystrix // 激活熔断机制
//@EnableCircuitBreaker // 激活熔断机制,通过这种方式,会集成一些cloud自带的功能,例如访问/health日志变多
@EnableFeignClients(clients = CatService.class)// 激活feign
public class AnimalConsumerBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(AnimalConsumerBootstrap.class,args);
    }


    /**
     * restTemplate方式
     * */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
       return new RestTemplate();
    }
}
