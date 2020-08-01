package com.spring.cloud.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/3/20 15:57
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZookeeperStart {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperStart.class,args);
    }
}
