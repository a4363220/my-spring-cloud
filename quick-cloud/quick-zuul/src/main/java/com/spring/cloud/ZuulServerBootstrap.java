package com.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 描述：
 *    网关服务启动类
 * @author 小谷
 * @Date 2020/1/16 17:42
 */
@SpringBootApplication
@EnableZuulProxy  // 激活网关代理
@EnableDiscoveryClient  // 激活服务发现客户端
public class ZuulServerBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerBootstrap.class,args);
    }
}
