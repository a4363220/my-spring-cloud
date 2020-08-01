package com.spring.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 描述：
 *     eureka server 启动引导类
 * @author 小谷
 * @Date 2020/1/16 16:06
 */
@SpringBootApplication
@EnableEurekaServer // 激活eureka服务端
public class EurekaServerBootstrapTest {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerBootstrapTest.class,args);
    }
}
