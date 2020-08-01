package com.spring.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/1/14 14:55
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient

public class ZuulServerBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerBootstrap.class,args);
    }
}
