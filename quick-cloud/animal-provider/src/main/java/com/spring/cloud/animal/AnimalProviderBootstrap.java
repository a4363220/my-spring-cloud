package com.spring.cloud.animal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 描述：
 *      动物提供者启动类
 * @author 小谷
 * @Date 2020/1/16 16:30
 */
@SpringBootApplication
@EnableDiscoveryClient // 激活服务发现客户端
public class AnimalProviderBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(AnimalProviderBootstrap.class,args);
    }
}
