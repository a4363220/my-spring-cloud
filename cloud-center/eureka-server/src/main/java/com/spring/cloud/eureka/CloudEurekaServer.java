package com.spring.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 描述：
 *     注册服务中心
 * @author 小谷
 * @Date 2020/1/12 14:30
 */
@SpringBootApplication
@EnableEurekaServer// 激活eureka服务端
public class CloudEurekaServer {

    public static void main(String[] args) {
        SpringApplication.run(CloudEurekaServer.class,args);
    }
}
