package com.spring.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/3/20 22:11
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServerStart {

    public static void main(String[] args) {
        SpringApplication.run(ServerStart.class,args);
    }
}
